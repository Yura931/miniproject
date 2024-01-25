package sideproject.fileservice.file.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sideproject.fileservice.file.dto.DeleteFileDto;
import sideproject.fileservice.file.dto.DeleteFileMappingDto;
import sideproject.fileservice.file.dto.FileDto;
import sideproject.fileservice.file.dto.FindFileDto;
import sideproject.fileservice.file.dto.response.FindFileResponse;
import sideproject.fileservice.file.dto.response.RegisterFileResponse;
import sideproject.fileservice.file.service.FileService;
import sideproject.fileservice.file.util.FileUtil;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class FileController {

    private final FileUtil fileUtil;
    private final FileService fileService;

    @PostMapping("/files/client/test")
    public ResponseEntity<String> filesClientTest(@RequestParam(name = "files") List<MultipartFile> files) {
        files.stream()
                .forEach(file -> file.getOriginalFilename());
        return ResponseEntity.ok().body("filesClientTest");
    }
    @PostMapping("/files/test")
    public ResponseEntity<String> filesTest(MultiValueMap<String, MultipartFile> multiValueMap) {
        return ResponseEntity.ok().body(Arrays.toString(multiValueMap.toSingleValueMap().values().toArray()));
    }
    @PostMapping("/files/register")
    public ResponseEntity<UUID> fileRegister(@RequestParam(name = "files") List<MultipartFile> files) throws Exception {
        List<FileDto> fileDtos = fileUtil.uploadFileDto(files);
        RegisterFileResponse registerFileResponse = fileService.save(fileDtos);
        return ResponseEntity.ok().body(registerFileResponse.getItems().fileMappingId());
    }

    @PostMapping("/files/update")
    public ResponseEntity<UUID> fileUpdate(@RequestParam(name = "files") List<MultipartFile> files, @RequestBody UUID fileMappingId) throws Exception {
        List<FileDto> fileDtos = fileUtil.uploadFileDto(files);
        RegisterFileResponse registerFileResponse = fileService.addFiles(fileDtos, fileMappingId);
        return ResponseEntity.ok().body(registerFileResponse.getItems().fileMappingId());
    }

    @GetMapping("/files")
    public ResponseEntity<List<FileDto>> files(@RequestBody UUID fileMappingId) {
        FindFileResponse findFileResponse = fileService.selectFiles(FindFileDto.from(fileMappingId));
        List<FileDto> fileDtos = findFileResponse.getItems().fileDtos();
        return ResponseEntity.ok().body(fileDtos);
    }

    @DeleteMapping("/files/{fileMappingId}")
    public ResponseEntity deleteFileMapping(@PathVariable UUID fileMappingId) {
        fileService.deleteFileMapping(DeleteFileMappingDto.from(fileMappingId));
        return ResponseEntity.ok().body("");
    }

    @DeleteMapping("/files")
    public ResponseEntity deleteFiles(@RequestBody List<UUID> ids) {
        fileService.deleteFiles(DeleteFileDto.from(ids));
        return ResponseEntity.ok().body("");
    }

}
