package sideproject.fileservice.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sideproject.fileservice.post.entity.PostFile;

import java.util.UUID;

public interface PostFileRepository extends JpaRepository<PostFile, UUID> {
}
