package sideproject.fileservice.file.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public FileMapping(UUID id, List<File> files) {
        this.id = id;
        this.files = files;
    }

    public static FileMapping createFileMapping(List<File> files) {
        return new FileMapping(
                UUID.randomUUID(), files
        );
    }
}
