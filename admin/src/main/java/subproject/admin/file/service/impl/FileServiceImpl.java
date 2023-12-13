package subproject.admin.file.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import subproject.admin.file.dto.FileDto;
import subproject.admin.file.entity.File;
import subproject.admin.file.repository.FileRepository;
import subproject.admin.file.service.FileService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;

    public void saveAll(List<FileDto> fileDtos) {
        List<File> files = File.createFiles(fileDtos);
        List<File> saveFiles = fileRepository.saveAll(files);
    }
}
