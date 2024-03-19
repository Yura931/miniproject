package sideproject.fileservice.file.dto;

import java.util.UUID;

public record FindFileDto (
        UUID fileOwnerId
) {

    public static FindFileDto from(UUID fileOwnerId) {
        return new FindFileDto(fileOwnerId);
    }
}
