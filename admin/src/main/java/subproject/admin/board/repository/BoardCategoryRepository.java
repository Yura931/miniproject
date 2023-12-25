package subproject.admin.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import subproject.admin.board.entity.Board;
import subproject.admin.board.entity.BoardCategory;

import java.util.UUID;

public interface BoardCategoryRepository extends JpaRepository<BoardCategory, UUID> {
    void deleteByIdAndBoardId(UUID id, Long boardId);
}
