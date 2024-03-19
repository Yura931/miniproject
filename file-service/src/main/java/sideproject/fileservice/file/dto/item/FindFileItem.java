package sideproject.fileservice.file.dto.item;

import sideproject.fileservice.file.dto.FileDto;

import java.util.List;

public record FindFileItem (
        List<FileDto> fileDtos
) {

}
