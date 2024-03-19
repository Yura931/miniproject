package sideproject.fileservice.common.entity;


import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@MappedSuperclass
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"originalFilename", "storedFilename", "filePath", "fileSize", "fileContentType", "fileExt", "downloadCount"})
public class File extends BaseEntity {
    protected String originalFilename;
    protected String storedFilename;
    protected String filePath;
    protected String fileSize;
    protected String fileContentType;
    protected String fileExt;
    protected Integer downloadCount;
}
