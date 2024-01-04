package sideproject.boardservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sideproject.boardservice.common.dto.FileDto;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "file-service")
public interface FileServiceClient {
    @GetMapping("/file-service/api/v1/files")
    ResponseEntity<List<FileDto>> files(@RequestBody UUID fileMappingId);
    @PostMapping("/file-service/api/v1/files/register")
    ResponseEntity<UUID> fileRegister(MultipartHttpServletRequest request);

    @PostMapping("/file-service/api/v1/files/update")
    ResponseEntity<UUID> fileUpdate(MultipartHttpServletRequest request, @RequestBody UUID fileMappingId);

    @DeleteMapping("/file-service/api/v1/file-mapping")
    ResponseEntity fileMappingDelete(@RequestBody UUID fileMappingId);

}
