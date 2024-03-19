package sideproject.fileservice.file.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sideproject.fileservice.common.entity.BaseTimeEntity;
import sideproject.fileservice.file.dto.FileDto;
import sideproject.fileservice.file.entity.enums.FileOwnerTypes;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Table(indexes = @Index(name = "idx__file_owner_id__file_owner_type", columnList = "file_owner_id, file_owner_type"))
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

    @Column(name = "file_owner_id")
    private UUID fileOwnerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "file_owner_type")
    private FileOwnerTypes fileOwnerType;

    public File(UUID id, String originalFilename, String storedFilename, String filePath, String fileSize, String fileContentType,
                String fileExt, Integer downloadCount, UUID fileOwnerId, FileOwnerTypes fileOwnerType) {
        this.id = id;
        this.originalFilename = originalFilename;
        this.storedFilename = storedFilename;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.fileContentType = fileContentType;
        this.fileExt = fileExt;
        this.downloadCount = downloadCount;
    }

    public static File createFile (FileDto dto) {
        return new File(
                UUID.randomUUID(),
                dto.originalFilename(),
                dto.storedFilename(),
                dto.filePath(),
                dto.fileSize(),
                dto.fileContentType(),
                dto.fileExt(),
                dto.downloadCount(),
                dto.fileOwnerId(),
                dto.fileOwnerTypes()
        );
    }

    public static List<File> createFiles (List<FileDto> fileDtoList) {
        return fileDtoList.stream()
                .map(dto -> new File(
                        UUID.randomUUID(),
                        dto.originalFilename(),
                        dto.storedFilename(),
                        dto.filePath(),
                        dto.fileSize(),
                        dto.fileContentType(),
                        dto.fileExt(),
                        dto.downloadCount(),
                        dto.fileOwnerId(),
                        dto.fileOwnerTypes()
                ))
                .toList();
    }
    public static List<File> updateFiles (List<FileDto> fileDtos) {
        return fileDtos.stream()
                .map(dto -> File.createFile(dto)
                )
                .toList();
    }

}
