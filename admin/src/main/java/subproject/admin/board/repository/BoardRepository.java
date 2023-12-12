package subproject.admin.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import subproject.admin.board.entity.Board;
import subproject.admin.board.entity.BoardCategoryMapping;
import subproject.admin.post.entity.Post;

import java.util.Optional;
import java.util.UUID;

public interface BoardRepository extends JpaRepository<Board, Long>, QuerydslPredicateExecutor {

    @EntityGraph(attributePaths = "boardCategoryMapping")
    Optional<Board> findById(Long uuid);
    Page<Board> findAll(Pageable pageable);
    void deleteByBoardCategoryMappingAndCategoryId(BoardCategoryMapping boardCategoryMapping, UUID categoryId);
}
