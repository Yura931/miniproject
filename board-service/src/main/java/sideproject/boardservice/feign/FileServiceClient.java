package sideproject.boardservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sideproject.boardservice.common.dto.FileDto;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "file-service")
public interface FileServiceClient {
    @GetMapping("/file-service/api/v1/files/{fileMappingId}")
    ResponseEntity<List<FileDto>> files(@PathVariable UUID fileMappingId);
    @PostMapping(value = "/api/v1/files/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<UUID> fileRegister(@RequestPart  List<MultipartFile> files);
    @PostMapping(value = "/api/v1/files/{fileMappingId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<UUID> fileUpdate(@RequestPart("files") List<MultipartFile> files, @PathVariable UUID fileMappingId);
    @DeleteMapping("/api/v1/files/{fileMappingId}")
    ResponseEntity fileMappingDelete(@PathVariable UUID fileMappingId);
    @PostMapping(value = "/api/v1/files/test", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<String> filesTest(@RequestPart List<MultipartFile> files);
    @PostMapping(value = "/api/v1/files/client/test", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<String> filesClientTest(@RequestPart(name = "files") List<MultipartFile> files);

}
