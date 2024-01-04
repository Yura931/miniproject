package sideproject.fileservice.file.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sideproject.fileservice.file.dto.DeleteFileDto;
import sideproject.fileservice.file.dto.DeleteFileMappingDto;
import sideproject.fileservice.file.dto.FileDto;
import sideproject.fileservice.file.dto.FindFileDto;
import sideproject.fileservice.file.dto.item.FindFileItem;
import sideproject.fileservice.file.dto.item.RegisterFileItem;
import sideproject.fileservice.file.dto.response.FindFileResponse;
import sideproject.fileservice.file.dto.response.RegisterFileResponse;
import sideproject.fileservice.file.entity.File;
import sideproject.fileservice.file.entity.FileMapping;
import sideproject.fileservice.file.repository.FileMappingRepository;
import sideproject.fileservice.file.repository.FileRepository;
import sideproject.fileservice.file.service.FileService;
import sideproject.fileservice.file.util.FileUtil;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileMappingRepository fileMappingRepository;
    private final FileRepository fileRepository;
    private final FileUtil fileUtil;

    public RegisterFileResponse save(List<FileDto> fileDtos) {
        FileMapping fileMapping = FileMapping.createFileMapping(fileDtos);
        FileMapping saveFiles = fileMappingRepository.save(fileMapping);
        RegisterFileItem registerFileItem = RegisterFileItem.fileEntityToDto(saveFiles);
        return new RegisterFileResponse(registerFileItem);
    }

    public FindFileResponse selectFiles(FindFileDto dto) {
        FileMapping fileMapping = fileMappingRepository.findById(dto.fileMappingId())
                .orElseThrow(EntityNotFoundException::new);

        FindFileItem findFileItem = FindFileItem.FileMappingEntityToDto(fileMapping);
        return new FindFileResponse(findFileItem);
    }

    public RegisterFileResponse addFiles(List<FileDto> fileDtos, UUID fileMappingId) {
        FileMapping fileMapping = fileMappingRepository.findById(fileMappingId)
                .orElseThrow(EntityNotFoundException::new);
        fileRepository.saveAll(File.updateFiles(fileMapping, fileDtos));
        RegisterFileItem registerFileItem = RegisterFileItem.fileEntityToDto(fileMapping);
        return new RegisterFileResponse(registerFileItem);
    }

    public void deleteFileMapping(DeleteFileMappingDto dto) {
        fileMappingRepository.deleteById(dto.fileMappingId());
    }
    public void deleteFiles(DeleteFileDto dto) {
        fileRepository.deleteAllById(dto.fileIds());
        List<File> files = fileRepository.findAllById(dto.fileIds());
        fileUtil.deleteFiles(files);
    }


}
