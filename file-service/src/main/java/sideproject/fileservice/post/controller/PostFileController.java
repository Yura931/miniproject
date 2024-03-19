package sideproject.fileservice.post.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sideproject.fileservice.post.dto.PostFileDto;
import sideproject.fileservice.post.dto.request.RegisterPostFileRequest;
import sideproject.fileservice.post.service.PostFileService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class PostFileController {

    private final PostFileService fileService;

    @PostMapping("/files/{postId}/register")
    public ResponseEntity<List<UUID>> fileRegister(@RequestParam(name = "files") List<MultipartFile> files, @PathVariable UUID postId) throws Exception {
//        List<FileDto> fileDto = fileUtil.uploadFileDto(files);
//        List<PostFileDto> postFileDtos = fileDto.stream()
//                .map(dto -> PostFileDto.of(dto, postId))
//                .toList();
//        RegisterPostFileResponse registerPostFileResponse = fileService.save(postFileDtos);
//        return ResponseEntity.ok().body(registerPostFileResponse.getItems().stream().map(item -> item.fileId()).toList());
        return ResponseEntity.ok().body(null);
    }

    @PostMapping("/files/update")
    public ResponseEntity<UUID> fileUpdate(@RequestParam(name = "files") List<MultipartFile> files, RegisterPostFileRequest request) throws Exception {
        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/files/{fileOwnerId}")
    public ResponseEntity<List<PostFileDto>> files(@PathVariable UUID fileOwnerId, @RequestBody String fileOwnerTypes) {
        return ResponseEntity.ok().body(null);
    }

    @DeleteMapping("/files/{fileMappingId}")
    public ResponseEntity deleteFileMapping(@PathVariable UUID fileMappingId) {
        return ResponseEntity.ok().body(null);
    }

    @DeleteMapping("/files")
    public ResponseEntity deleteFiles(@RequestBody List<UUID> ids) {
        return ResponseEntity.ok().body(null);
    }

}
