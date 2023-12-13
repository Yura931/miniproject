package subproject.admin.file.service;

import subproject.admin.file.dto.FileDto;

import java.util.List;

public interface FileService {
    void saveAll(List<FileDto> dto);
}
