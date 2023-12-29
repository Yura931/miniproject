package sideproject.fileservice.file.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sideproject.fileservice.file.dto.FileDto;
import sideproject.fileservice.file.dto.response.RegisterFileResponse;
import sideproject.fileservice.file.entity.File;
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
    @PostMapping("/file/register")
    public ResponseEntity<UUID> fileRegister(MultipartHttpServletRequest request) throws Exception {
        List<FileDto> fileDtos = fileUtil.uploadFileDto(request);
        RegisterFileResponse registerFileResponse = fileService.save(fileDtos);
        return ResponseEntity.ok().body(registerFileResponse.getItems().fileMappingId());
    }
}
