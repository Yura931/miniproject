package sideproject.fileservice.file.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sideproject.fileservice.file.entity.File;
import sideproject.fileservice.file.entity.FileMapping;

import java.util.UUID;

public interface FileRepository extends JpaRepository<FileMapping, UUID> {
}
