package sideproject.fileservice.file.dto;

import java.util.UUID;

public record DeleteFileMappingDto(
        UUID fileMappingId
) {

    public static DeleteFileMappingDto from (UUID fileMappingId) {
        return new DeleteFileMappingDto(fileMappingId);
    }
}
