package sideproject.fileservice.post.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sideproject.fileservice.common.dto.FileDto;
import sideproject.fileservice.common.util.FileUploadUtil;
import sideproject.fileservice.post.dto.PostFileDto;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(value = false)
class PostFileTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    FileUploadUtil fileUtil;
    UUID postId = UUID.randomUUID();

    @BeforeEach
    void beforeEach() throws IOException {
        final String fileName = "testImage"; //파일명
        final String contentType = "png"; //파일타입

        List<MultipartFile> multipartFileList = IntStream.range(0, 1)
                .mapToObj(i -> new MockMultipartFile(
                                "images" + i,
                                fileName + i + '.' + contentType,
                                contentType,
                                "contents".getBytes()
                        ))
                .collect(Collectors.toList());

        List<FileDto> fileDtos = fileUtil.uploadFileDto(multipartFileList);


        List<PostFileDto> postFileDtos = fileDtos.stream()
                .map(fileDto -> PostFileDto.of(fileDto, postId))
                .toList();

        System.out.println("postFileDtos = " + postFileDtos);
        List<PostFile> postFiles = PostFile.createFiles(postFileDtos);
        postFiles.stream()
                .forEach(file -> em.persist(file));

        em.flush();
        em.clear();
    }

    @Test
    @DisplayName("파일 조회 테스트")
    void fileSelectTest() {
        List<PostFile> fileList = em.createQuery("select pf from PostFile pf", PostFile.class)
                .getResultList();

        assertThat(fileList).size().isEqualTo(3);
    }

    @Test
    @DisplayName("파일 추가 테스트")
    void fileAddTest() {
        PostFileDto postFileDto = PostFileDto.of(
                FileDto.of("originalFilename", "storedFilename"
                        , "filePath", "fileSize", "fileContentType", "fileExt", 0), postId
        );

        PostFile file = PostFile.createFile(postFileDto);
        em.persist(file);

        List<PostFile> fileList = em.createQuery("select pf from PostFile pf", PostFile.class)
                .getResultList();
        assertThat(fileList).size().isEqualTo(11);
    }
}