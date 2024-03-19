package sideproject.fileservice.file.dto.item;

import sideproject.fileservice.file.entity.File;
import sideproject.fileservice.file.entity.enums.FileOwnerTypes;

import java.util.List;
import java.util.UUID;

public record RegisterFileItem (
        UUID fileId,
        UUID fileOwnerId,
        FileOwnerTypes fileOwnerTypes
) {
    public static List<RegisterFileItem> fileEntityToDto(List<File> fileList) {
        return fileList.stream()
                .map(file -> new RegisterFileItem(file.getId(), file.getFileOwnerId(), file.getFileOwnerType()))
                .toList();
    }
}
