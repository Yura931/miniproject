package sideproject.fileservice.file.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sideproject.fileservice.file.dto.FileDto;

import java.util.ArrayList;
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
    private List<File> files = new ArrayList<>();

    public FileMapping(UUID id, List<FileDto> files) {
        this.id = id;
        this.files = files.stream()
                .map(file -> File.createFile(file, this))
                .toList();

    }

    public static FileMapping createFileMapping(List<FileDto> files) {
        return new FileMapping(
                UUID.randomUUID(),
                files
        );
    }
}
