package sideproject.boardservice.board.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import sideproject.boardservice.board.entity.BoardCategory;

import java.util.UUID;

public interface BoardCategoryRepository extends JpaRepository<BoardCategory, UUID> {
    void deleteByIdAndBoardId(UUID id, Long boardId);
}
