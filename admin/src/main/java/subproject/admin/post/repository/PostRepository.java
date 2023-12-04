package subproject.admin.post.repository;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import subproject.admin.board.entity.Board;
import subproject.admin.post.entity.Post;

import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
    Page<Post> findAllByBoard(Pageable pageable, @Param("board") Board board);
}
