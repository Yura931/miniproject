package subproject.admin.file.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import subproject.admin.common.entity.BaseEntity;
import subproject.admin.file.dto.FileDto;
import subproject.admin.post.entity.Post;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File extends BaseEntity {

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
    @JoinColumn(name = "post_id")
    private Post post;

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

    public static List<File> createFiles (List<FileDto> fileDtos) {
        return fileDtos.stream()
                .map(dto -> new File(
                        UUID.randomUUID(),
                        dto.originalFilename(),
                        dto.storedFilename(),
                        dto.filePath(),
                        dto.fileSize(),
                        dto.fileContentType(),
                        dto.fileExt(),
                        dto.downloadCount()
                ))
                .toList();
    }

    public File addPost(Post post) {
        this.post = post;
        return this;
    }
}
