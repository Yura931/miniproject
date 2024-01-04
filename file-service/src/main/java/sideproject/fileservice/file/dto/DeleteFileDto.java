package sideproject.fileservice.file.dto;

import java.util.List;
import java.util.UUID;

public record DeleteFileDto(
        List<UUID> fileIds
) {
    public static DeleteFileDto from (List<UUID> fileIds) {
        return new DeleteFileDto(fileIds);
    }
}
