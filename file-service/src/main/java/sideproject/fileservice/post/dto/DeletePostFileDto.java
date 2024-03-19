package sideproject.fileservice.post.dto;

import java.util.List;
import java.util.UUID;

public record DeletePostFileDto(
        List<UUID> fileIds
) {
    public static DeletePostFileDto from (List<UUID> fileIds) {
        return new DeletePostFileDto(fileIds);
    }
}
