package subproject.admin.file.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import subproject.admin.file.entity.File;
import subproject.admin.post.entity.Post;

import java.util.List;
import java.util.UUID;

public interface FileRepository extends JpaRepository<File, UUID> {
    List<File> findByPost(Post post);

}
