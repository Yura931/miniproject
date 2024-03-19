package sideproject.fileservice.post.dto.item;

import sideproject.fileservice.post.entity.PostFile;

import java.util.List;
import java.util.UUID;

public record RegisterPostFileItem(
        UUID fileId,
        UUID fileOwnerId
) {
    public static List<RegisterPostFileItem> fileEntityToDto(List<PostFile> fileList) {
        return null;
    }
}
