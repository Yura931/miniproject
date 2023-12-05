package subproject.admin.common.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import subproject.admin.common.dto.FileDto;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File {

    @Id
    @Column(name = "file_id")
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    private FileMapping fileMapping;

    private String originalFilename;

    private String storedFilename;

    private String filePath;

    private String fileSize;

    private String fileContentType;

    private String fileExt;

    private Integer downloadCount;

    public File(UUID id, String originalFilename, String storedFilename, String filePath, String fileSize, String fileContentType, String fileExt, Integer downloadCount) {
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
        UUID id = UUID.randomUUID();
        return new File(
                id,
                dto.originalFilename(),
                dto.storedFilename(),
                dto.filePath(),
                dto.fileSize(),
                dto.fileContentType(),
                dto.fileExt(),
                dto.downloadCount()
        );
    }
}
