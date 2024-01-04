package sideproject.fileservice.file.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sideproject.fileservice.file.entity.FileMapping;

import java.util.UUID;

public interface FileMappingRepository extends JpaRepository<FileMapping, UUID> {
}
