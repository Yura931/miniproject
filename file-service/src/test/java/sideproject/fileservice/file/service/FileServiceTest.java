package sideproject.fileservice.file.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import sideproject.fileservice.file.dto.FileDto;
import sideproject.fileservice.file.dto.FindFileDto;
import sideproject.fileservice.file.dto.item.RegisterFileItem;
import sideproject.fileservice.file.dto.response.FindFileResponse;
import sideproject.fileservice.file.dto.response.RegisterFileResponse;
import sideproject.fileservice.file.entity.File;
import sideproject.fileservice.file.entity.FileMapping;
import sideproject.fileservice.file.repository.FileMappingRepository;
import sideproject.fileservice.file.repository.FileRepository;
import sideproject.fileservice.file.util.FileUtil;

import java.nio.charset.StandardCharsets;
import java.util.List;

@SpringBootTest
@Transactional
@Rollback(value = false)
class FileServiceTest {

    @Autowired
    FileService fileService;

    @Autowired
    FileMappingRepository fileMappingRepository;

    @Autowired
    FileRepository fileRepository;

    @Autowired
    FileUtil fileUtil;

    @Test
    @DisplayName("fileSaveAll")
    @Order(1)
    void fileSaveTest() throws Exception {
        final String fileName = "testImage1";
        final String contentType = "png";

        MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
        MockMultipartFile file1 = new MockMultipartFile(
                "file1",
                "test.txt",
                "text/plain",
                "File content".getBytes(StandardCharsets.UTF_8)
        );

        MockMultipartFile file2 = new MockMultipartFile(
                "file2",
                "image.png",
                "png",
                "File ".getBytes(StandardCharsets.UTF_8)
        );

        request.addFile(file1);
        request.addFile(file2);
        List<FileDto> fileDtos = fileUtil.uploadFileDto(request);
        RegisterFileResponse saveFile = fileService.save(fileDtos);
        RegisterFileItem items = saveFile.getItems();
        FindFileResponse findFileResponse = fileService.selectFiles(FindFileDto.from(items.fileMappingId()));
        List<FileDto> findFileDto = findFileResponse.getItems().fileDtos();
        Assertions.assertThat(findFileDto.size()).isEqualTo(2);

        FileMapping fileMapping = fileMappingRepository.findById(items.fileMappingId()).get();

        MockMultipartFile file3 = new MockMultipartFile(
                "file3",
                "test.txt",
                "text/plain",
                "File content".getBytes(StandardCharsets.UTF_8)
        );

        MockMultipartFile file4 = new MockMultipartFile(
                "file4",
                "image.png",
                "png",
                "File ".getBytes(StandardCharsets.UTF_8)
        );

        request.addFile(file1);
        request.addFile(file2);
        List<FileDto> fileDtos2 = fileUtil.uploadFileDto(request);
        List<File> files = File.updateFiles(fileMapping, fileDtos2);
        List<File> files1 = fileRepository.saveAll(files);

        Assertions.assertThat(findFileDto.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("")
    @Order(2)
    void fileUpdateTest() throws Exception {
        MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
        MockMultipartFile file1 = new MockMultipartFile(
                "file1",
                "test.txt",
                "text/plain",
                "File content".getBytes(StandardCharsets.UTF_8)
        );

        MockMultipartFile file2 = new MockMultipartFile(
                "file2",
                "image.png",
                "png",
                "File ".getBytes(StandardCharsets.UTF_8)
        );

        request.addFile(file1);
        request.addFile(file2);
        List<FileDto> fileDtos = fileUtil.uploadFileDto(request);
        FileMapping fileMapping = fileMappingRepository.findAll().get(0);

        File.updateFiles(fileMapping, fileDtos);
        Assertions.assertThat(fileMapping.getFiles()).size().isEqualTo(4);
    }
}