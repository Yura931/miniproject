package sideproject.fileservice.post.dto;

import sideproject.fileservice.common.dto.FileDto;
import sideproject.fileservice.post.entity.PostFile;

import java.util.UUID;

public record PostFileDto(
        // 원본 명
        String originalFilename,
        // 저장 명
        String storedFilename,
        String filePath,
        String fileSize,

        String fileContentType,
        String fileExt,
        Integer downloadCount,
        UUID postId
) {
    public static PostFileDto of(FileDto dto, UUID postId) {
        return new PostFileDto(
                dto.originalFilename(),
                dto.storedFilename(),
                dto.filePath(),
                dto.fileSize(),
                dto.fileContentType(),
                dto.fileExt(),
                dto.downloadCount(),
                postId
        );
    }
}
