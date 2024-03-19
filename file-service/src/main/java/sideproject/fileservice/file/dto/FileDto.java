package sideproject.fileservice.file.dto;

import sideproject.fileservice.file.entity.File;
import sideproject.fileservice.file.entity.enums.FileOwnerTypes;

import java.util.UUID;

public record FileDto(
        // 원본 명
        String originalFilename,
        // 저장 명
        String storedFilename,
        String filePath,
        String fileSize,

        String fileContentType,
        String fileExt,
        Integer downloadCount,

        UUID fileOwnerId,
        FileOwnerTypes fileOwnerTypes
) {

    public static FileDto of(String originalFilename, String storedFilename,
                             String filePath, String fileSize, String fileContentType,
                             String fileExt, Integer downloadCount, UUID fileOwnerId, FileOwnerTypes fileOwnerTypes) {
        return new FileDto(
                originalFilename,
                storedFilename,
                filePath,
                fileSize,
                fileContentType,
                fileExt,
                downloadCount,
                fileOwnerId,
                fileOwnerTypes
        );
    }

    public static FileDto from(File file) {
        return new FileDto(
                file.getOriginalFilename(),
                file.getStoredFilename(),
                file.getFilePath(),
                file.getFileSize(),
                file.getFileContentType(),
                file.getFileExt(),
                file.getDownloadCount(),
                file.getFileOwnerId(),
                file.getFileOwnerType()
        );
    }
}
