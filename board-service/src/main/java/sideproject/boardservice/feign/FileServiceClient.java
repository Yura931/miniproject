package sideproject.boardservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sideproject.boardservice.common.dto.FileDto;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "file-service")
public interface FileServiceClient {
    @GetMapping("/api/v1/files")
    ResponseEntity<List<FileDto>> files(@RequestBody UUID fileMappingId);
    @PostMapping("/api/v1/files/register")
    ResponseEntity<UUID> fileRegister(MultipartHttpServletRequest request);

    @PostMapping("/api/v1/files/{fileMappingId}")
    ResponseEntity<UUID> fileUpdate(MultipartHttpServletRequest request, @PathVariable UUID fileMappingId);

    @DeleteMapping("/api/v1/file-mapping")
    ResponseEntity fileMappingDelete(@RequestBody UUID fileMappingId);

}
