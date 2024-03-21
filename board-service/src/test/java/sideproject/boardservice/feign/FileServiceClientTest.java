package sideproject.boardservice.feign;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sideproject.boardservice.common.dto.FileDto;
import sideproject.boardservice.config.TestFeignConfig;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
public class FileServiceClientTest {

    @Autowired
    TestFileServiceClient testFileServiceClient;

    @Test
    @DisplayName("")
    void fileServiceRequestTest() throws Exception {

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
        final UUID requestPostId = UUID.randomUUID();
        ResponseEntity<UUID> uuidResponseEntity = testFileServiceClient.fileRegister(multipartFileList, requestPostId);
        UUID postId = uuidResponseEntity.getBody();
        System.out.println("postId = " + postId);
    }

    @FeignClient(name = "http://localhost:8000/file-service", contextId = "feignClientForMall", configuration = {TestFeignConfig.class})
    public interface TestFileServiceClient {
        @GetMapping("/api/v1/post/files/{postId}")
        ResponseEntity<List<FileDto>> files(@PathVariable UUID postId, @RequestBody String fileOwnerType);
        @PostMapping(value = "/api/v1/post/files/{postId}/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        ResponseEntity<UUID> fileRegister(@RequestPart List<MultipartFile> files, @PathVariable UUID postId);
        @DeleteMapping(value = "/api/v1/post/files")
        ResponseEntity<UUID> deleteFiles(@RequestBody List<UUID> postFileIds);
        @DeleteMapping("/api/v1/post/files/{postId}")
        ResponseEntity deleteFile(@PathVariable UUID postId);

    }
}
