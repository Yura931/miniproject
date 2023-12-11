package subproject.admin.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import subproject.admin.board.entity.Board;
import subproject.admin.post.entity.Post;

import java.util.Optional;
import java.util.UUID;

public interface BoardRepository extends JpaRepository<Board, UUID>, QuerydslPredicateExecutor {

    @EntityGraph(attributePaths = "boardCategory")
    Optional<Board> findById(UUID uuid);
    Page<Board> findAll(Pageable pageable);

}
