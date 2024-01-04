package sideproject.fileservice.file.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sideproject.fileservice.file.dto.DeleteFileDto;
import sideproject.fileservice.file.dto.DeleteFileMappingDto;
import sideproject.fileservice.file.dto.FileDto;
import sideproject.fileservice.file.dto.FindFileDto;
import sideproject.fileservice.file.dto.response.FindFileResponse;
import sideproject.fileservice.file.dto.response.RegisterFileResponse;
import sideproject.fileservice.file.service.FileService;
import sideproject.fileservice.file.util.FileUtil;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class FileController {

    private final FileUtil fileUtil;
    private final FileService fileService;
    @PostMapping("/files/register")
    public ResponseEntity<UUID> fileRegister(MultipartHttpServletRequest request) throws Exception {
        List<FileDto> fileDtos = fileUtil.uploadFileDto(request);
        RegisterFileResponse registerFileResponse = fileService.save(fileDtos);
        return ResponseEntity.ok().body(registerFileResponse.getItems().fileMappingId());
    }

    @PostMapping("/files/update")
    public ResponseEntity<UUID> fileUpdate(MultipartHttpServletRequest request, @RequestBody UUID fileMappingId) throws Exception {
        List<FileDto> fileDtos = fileUtil.uploadFileDto(request);
        RegisterFileResponse registerFileResponse = fileService.addFiles(fileDtos, fileMappingId);
        return ResponseEntity.ok().body(registerFileResponse.getItems().fileMappingId());
    }

    @GetMapping("/files")
    public ResponseEntity<List<FileDto>> files(@RequestBody UUID fileMappingId) {
        FindFileResponse findFileResponse = fileService.selectFiles(FindFileDto.from(fileMappingId));
        List<FileDto> fileDtos = findFileResponse.getItems().fileDtos();
        return ResponseEntity.ok().body(fileDtos);
    }

    @DeleteMapping("/files/file-mapping")
    public ResponseEntity deleteFileMapping(@RequestBody UUID fileMappingId) {
        fileService.deleteFileMapping(DeleteFileMappingDto.from(fileMappingId));
        return ResponseEntity.ok().body("");
    }

    @DeleteMapping("/files")
    public ResponseEntity deleteFiles(@RequestBody List<UUID> ids) {
        fileService.deleteFiles(DeleteFileDto.from(ids));
        return ResponseEntity.ok().body("");
    }

}
