package sideproject.boardservice.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class FileDto {
    private String originalFilename;
    private String storedFilename;
    private String filePath;
    private String fileSize;
    private String fileContentType;
    private String fileExt;
    private Integer downloadCount;
}
