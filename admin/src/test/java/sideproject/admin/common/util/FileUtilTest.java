package sideproject.admin.common.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import sideproject.admin.file.dto.FileDto;
import sideproject.admin.file.util.FileUtil;

import java.nio.charset.StandardCharsets;
import java.util.List;

@SpringBootTest
class FileUtilTest {

    @Autowired
    FileUtil fileUtil;

    @Test
    public void localFileUploadTest() throws Exception {
        MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "test.txt",
                "text/plain",
                "File content".getBytes(StandardCharsets.UTF_8)
        );

        request.addFile(file);
        List<FileDto> fileDtos = fileUtil.uploadFileDto(request);
        System.out.println("fileDtos = " + fileDtos);
    }

}