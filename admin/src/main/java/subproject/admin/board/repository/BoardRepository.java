package subproject.admin.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import subproject.admin.board.entity.Board;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long>, QuerydslPredicateExecutor {

    @EntityGraph(attributePaths = "categories")
    Optional<Board> findById(Long id);
    Page<Board> findAll(Pageable pageable);
}
