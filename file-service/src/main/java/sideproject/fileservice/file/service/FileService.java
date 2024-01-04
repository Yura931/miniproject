package sideproject.fileservice.file.service;

import sideproject.fileservice.file.dto.DeleteFileDto;
import sideproject.fileservice.file.dto.DeleteFileMappingDto;
import sideproject.fileservice.file.dto.FileDto;
import sideproject.fileservice.file.dto.FindFileDto;
import sideproject.fileservice.file.dto.response.FindFileResponse;
import sideproject.fileservice.file.dto.response.RegisterFileResponse;

import java.util.List;
import java.util.UUID;

public interface FileService {
    RegisterFileResponse save(List<FileDto> fileDtos);
    FindFileResponse selectFiles(FindFileDto dto);
    RegisterFileResponse addFiles(List<FileDto> fileDtos, UUID fileMappingId);
    void deleteFileMapping(DeleteFileMappingDto dto);
    void deleteFiles(DeleteFileDto dto);
}
