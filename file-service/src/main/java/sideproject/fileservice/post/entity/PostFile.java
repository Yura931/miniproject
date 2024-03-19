package sideproject.fileservice.post.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import sideproject.fileservice.common.entity.File;
import sideproject.fileservice.post.dto.PostFileDto;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Table(
        indexes = @Index(name = "idx__post_id", columnList = "post_id")
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostFile extends File {

    @Id
    @Column(name = "post_file_id")
    private UUID id;

    @Column(name = "post_id")
    private UUID postId;

    public PostFile(UUID id, String originalFilename, String storedFilename, String filePath, String fileSize, String fileContentType,
                    String fileExt, Integer downloadCount, UUID postId) {
        this.id = id;
        this.originalFilename = originalFilename;
        this.storedFilename = storedFilename;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.fileContentType = fileContentType;
        this.fileExt = fileExt;
        this.downloadCount = downloadCount;
        this.postId = postId;
    }

    public static PostFile createFile (PostFileDto dto) {
        return new PostFile(
                UUID.randomUUID(),
                dto.originalFilename(),
                dto.storedFilename(),
                dto.filePath(),
                dto.fileSize(),
                dto.fileContentType(),
                dto.fileExt(),
                dto.downloadCount(),
                dto.postId()
        );
    }

    public static List<PostFile> createFiles (List<PostFileDto> postFileDtoList) {
        return postFileDtoList.stream()
                .map(dto -> new PostFile(
                        UUID.randomUUID(),
                        dto.originalFilename(),
                        dto.storedFilename(),
                        dto.filePath(),
                        dto.fileSize(),
                        dto.fileContentType(),
                        dto.fileExt(),
                        dto.downloadCount(),
                        dto.postId()
                ))
                .toList();
    }
    public static List<PostFile> updateFiles (List<PostFileDto> postFileList) {
        return postFileList.stream()
                .map(dto -> PostFile.createFile(dto)
                )
                .toList();
    }

    @Override
    public String toString() {
        return "PostFile{" +
                "id=" + id +
                ", postId=" + postId +
                ", originalFilename='" + originalFilename + '\'' +
                ", storedFilename='" + storedFilename + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileSize='" + fileSize + '\'' +
                ", fileContentType='" + fileContentType + '\'' +
                ", fileExt='" + fileExt + '\'' +
                ", downloadCount=" + downloadCount +
                '}';
    }
}
