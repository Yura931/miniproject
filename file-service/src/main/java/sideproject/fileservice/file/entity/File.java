package sideproject.fileservice.file.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sideproject.fileservice.common.entity.BaseTimeEntity;
import sideproject.fileservice.file.dto.FileDto;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File extends BaseTimeEntity {

    @Id
    @Column(name = "file_id")
    private UUID id;

    private String originalFilename;
    private String storedFilename;
    private String filePath;
    private String fileSize;
    private String fileContentType;
    private String fileExt;
    private Integer downloadCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_mapping_id")
    private FileMapping fileMapping;

    public File(UUID id, String originalFilename, String storedFilename, String filePath, String fileSize, String fileContentType, String fileExt, Integer downloadCount, FileMapping fileMapping) {
        this.id = id;
        this.originalFilename = originalFilename;
        this.storedFilename = storedFilename;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.fileContentType = fileContentType;
        this.fileExt = fileExt;
        this.downloadCount = downloadCount;
        this.fileMapping = fileMapping;
    }

    public static File createFile (FileDto dto, FileMapping fileMapping) {
        return new File(
                UUID.randomUUID(),
                dto.originalFilename(),
                dto.storedFilename(),
                dto.filePath(),
                dto.fileSize(),
                dto.fileContentType(),
                dto.fileExt(),
                dto.downloadCount(),
                fileMapping
        );
    }
    public static List<File> updateFiles (FileMapping fileMapping, List<FileDto> fileDtos) {
        return fileDtos.stream()
                .map(dto -> File.createFile(dto, fileMapping)
                )
                .toList();
    }

}
