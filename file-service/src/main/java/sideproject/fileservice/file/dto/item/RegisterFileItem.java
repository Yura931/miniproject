package sideproject.fileservice.file.dto.item;

import sideproject.fileservice.file.dto.FileDto;
import sideproject.fileservice.file.entity.File;
import sideproject.fileservice.file.entity.FileMapping;

import java.util.List;
import java.util.UUID;

public record RegisterFileItem (
        UUID fileMappingId
) {
    public static RegisterFileItem FileEntityToDto(FileMapping fileMapping) {
        return new RegisterFileItem(fileMapping.getId());
    }
}
