package sideproject.fileservice.file.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sideproject.fileservice.file.dto.FileDto;
import sideproject.fileservice.file.dto.item.RegisterFileItem;
import sideproject.fileservice.file.dto.response.RegisterFileResponse;
import sideproject.fileservice.file.entity.File;
import sideproject.fileservice.file.repository.FileRepository;
import sideproject.fileservice.file.service.FileService;
import sideproject.fileservice.file.util.FileUtil;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;
    private final FileUtil fileUtil;

    public RegisterFileResponse save(List<FileDto> fileDtoList) {
        List<File> files = File.createFiles(fileDtoList);
        List<File> saveFiles = fileRepository.saveAll(files);
        return new RegisterFileResponse(RegisterFileItem.fileEntityToDto(saveFiles));
    }

}
