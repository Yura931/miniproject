package sideproject.fileservice.file.dto.item;

import sideproject.fileservice.file.dto.FileDto;
import sideproject.fileservice.file.dto.FindFileDto;
import sideproject.fileservice.file.entity.FileMapping;

import java.util.List;

public record FindFileItem (
        List<FileDto> fileDtos
) {
    public static FindFileItem FileMappingEntityToDto(FileMapping fileMapping) {
        return new FindFileItem (fileMapping.getFiles().stream()
                .map(file -> FileDto.from(file))
                .toList());
    }
}
