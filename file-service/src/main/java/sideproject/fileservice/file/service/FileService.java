package sideproject.fileservice.file.service;

import sideproject.fileservice.file.dto.FileDto;
import sideproject.fileservice.file.dto.response.RegisterFileResponse;

import java.util.List;

public interface FileService {
    RegisterFileResponse save(List<FileDto> fileDtos);
}
