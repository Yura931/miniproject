package sideproject.fileservice.file.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sideproject.fileservice.file.dto.FileDto;
import sideproject.fileservice.file.dto.request.RegisterFileRequest;
import sideproject.fileservice.file.dto.response.RegisterFileResponse;
import sideproject.fileservice.file.entity.enums.FileOwnerTypes;
import sideproject.fileservice.file.service.FileService;
import sideproject.fileservice.file.util.FileUtil;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class FileController {

    private final FileUtil fileUtil;
    private final FileService fileService;

    @PostMapping("/files/register")
    public ResponseEntity<List<UUID>> fileRegister(@RequestParam(name = "files") List<MultipartFile> files, RegisterFileRequest request) throws Exception {
        List<FileDto> fileDtos = fileUtil.uploadFileDto(files, request.getFileOwnerId(), request.getFileOwnerTypes());
        RegisterFileResponse registerFileResponse = fileService.save(fileDtos);
        return ResponseEntity.ok().body(registerFileResponse.getItems().stream().map(item -> item.fileId()).toList());
    }

    @PostMapping("/files/update")
    public ResponseEntity<UUID> fileUpdate(@RequestParam(name = "files") List<MultipartFile> files, RegisterFileRequest request) throws Exception {
        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/files/{fileOwnerId}")
    public ResponseEntity<List<FileDto>> files(@PathVariable UUID fileOwnerId, @RequestBody String fileOwnerTypes) {
        FileOwnerTypes.valueOf(fileOwnerTypes);
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
