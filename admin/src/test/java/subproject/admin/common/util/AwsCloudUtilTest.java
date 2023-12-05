package subproject.admin.common.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import subproject.admin.common.dto.FileDto;

import java.nio.charset.StandardCharsets;
import java.util.List;

@SpringBootTest
class AwsCloudUtilTest {

    @Autowired
    S3Util awsCloudUtil;

    @Test
    public void awsCloudUploadFileTest() {
        MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "test.txt",
                "text/plain",
                "File content".getBytes(StandardCharsets.UTF_8)
        );

        request.addFile(file);
        List<FileDto> fileDtos = awsCloudUtil.uploadFileDto(request);
        System.out.println("fileDtos = " + fileDtos);
    }
}