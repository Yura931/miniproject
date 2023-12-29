package sideproject.fileservice.file.dto;

import java.util.UUID;

public record FindFileDto (
        UUID fileMappingId
) {

    public static FindFileDto from(UUID fileMappingId) {
        return new FindFileDto(fileMappingId);
    }
}
