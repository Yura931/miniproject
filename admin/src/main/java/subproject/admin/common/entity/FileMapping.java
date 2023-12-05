package subproject.admin.common.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import subproject.admin.common.dto.FileDto;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FileMapping {

    @Id
    @Column(name = "file_mapping_id")
    private UUID id;

    @OneToMany(mappedBy = "fileMapping", cascade = CascadeType.ALL)
    private List<File> file;

    public FileMapping(UUID id, List<FileDto> files) {
        this.id = id;
        this.file = files.stream()
                .map(dto -> File.createFile(dto))
                .toList();
    }

    public static FileMapping createFileMapping(List<FileDto> dtos) {
        UUID id = UUID.randomUUID();
        return new FileMapping(id, dtos);
    }
}
