package sideproject.fileservice.file.dto.item;

import sideproject.fileservice.file.entity.FileMapping;

import java.util.UUID;

public record RegisterFileItem (
        UUID fileMappingId
) {
    public static RegisterFileItem fileEntityToDto(FileMapping fileMapping) {
        return new RegisterFileItem(fileMapping.getId());
    }
}
