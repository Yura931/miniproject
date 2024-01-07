package sideproject.admin.file.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sideproject.admin.file.entity.File;
import java.util.UUID;

public interface FileRepository extends JpaRepository<File, UUID> {


}
