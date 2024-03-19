package sideproject.fileservice.post.dto;

import java.util.UUID;

public record FindPostFileDto(
        UUID fileOwnerId
) {

    public static FindPostFileDto from(UUID fileOwnerId) {
        return new FindPostFileDto(fileOwnerId);
    }
}
