package sideproject.fileservice.file.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sideproject.fileservice.file.dto.FileDto;
import sideproject.fileservice.file.dto.FindFileDto;
import sideproject.fileservice.file.dto.item.FindFileItem;
import sideproject.fileservice.file.dto.item.RegisterFileItem;
import sideproject.fileservice.file.dto.response.FindFileResponse;
import sideproject.fileservice.file.dto.response.RegisterFileResponse;
import sideproject.fileservice.file.entity.File;
import sideproject.fileservice.file.entity.FileMapping;
import sideproject.fileservice.file.repository.FileRepository;
import sideproject.fileservice.file.service.FileService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    public RegisterFileResponse save(List<FileDto> fileDtos) {
        List<File> files = File.createFiles(fileDtos);
        FileMapping fileMapping = FileMapping.createFileMapping(files);
        FileMapping saveFiles = fileRepository.save(fileMapping);
        RegisterFileItem registerFileItem = RegisterFileItem.FileEntityToDto(saveFiles);
        return new RegisterFileResponse(registerFileItem);
    }

    public FindFileResponse findById(FindFileDto dto) {
        FileMapping fileMapping = fileRepository.findById(dto.fileMappingId())
                .orElseThrow(EntityNotFoundException::new);

        FindFileItem findFileItem = FindFileItem.FileMappingEntityToDto(fileMapping);
        return new FindFileResponse(findFileItem);
    }
}
