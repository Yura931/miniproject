package sideproject.fileservice.common.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import sideproject.fileservice.common.dto.FileDto;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FileDownLoadUtilTest {


    @Autowired
    FileUploadUtil fileUploadUtil;

    @Autowired
    FileDownLoadUtil fileDownLoadUtil;

    @BeforeEach
    void beforeEach() throws Exception {
        final String fileName = "testImage"; //파일명
        final String contentType = "png"; //파일타입

        List<MultipartFile> multipartFileList = IntStream.range(0, 3)
                .mapToObj(i -> new MockMultipartFile(
                        "images" + i,
                        fileName + i + '.' + contentType,
                        contentType,
                        "contents".getBytes()
                ))
                .collect(Collectors.toList());

        List<FileDto> fileDtos = fileUploadUtil.uploadFileDto(multipartFileList);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("User-Agent", "Chrome");
        MockHttpServletResponse response = new MockHttpServletResponse();

        fileDownLoadUtil.fileDownLoad(fileDtos.get(0), request, response);
        System.out.println("response = " + response.getHeader("Content-Disposition"));
    }

    @Test
    @DisplayName("")
    void fileDownLoadUtilTest() {

    }
}