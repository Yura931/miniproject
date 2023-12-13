package subproject.admin.file.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import subproject.admin.file.dto.FileDto;
import subproject.admin.file.entity.File;
import subproject.admin.file.repository.FileRepository;
import subproject.admin.file.util.FileUtil;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FileServiceTest {
    @Autowired
    FileRepository fileRepository;

    @Autowired
    FileUtil fileUtil;

    @Test
    public void saveAllTest() throws Exception {
        MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
        MockMultipartFile file1 = new MockMultipartFile(
                "file1",
                "test.txt",
                "text/plain",
                "File content".getBytes(StandardCharsets.UTF_8)
        );

        MockMultipartFile file2 = new MockMultipartFile(
                "file2",
                "test.txt",
                "text/plain",
                "File content".getBytes(StandardCharsets.UTF_8)
        );

        MockMultipartFile file3 = new MockMultipartFile(
                "file3",
                "test.txt",
                "text/plain",
                "File content".getBytes(StandardCharsets.UTF_8)
        );

        request.addFile(file1);
        request.addFile(file2);
        request.addFile(file3);

        List<FileDto> fileDtos = fileUtil.uploadFileDto(request);
        List<File> files = File.createFiles(fileDtos);
        fileRepository.saveAll(files);
        // given
        // when
        // then
    }
}