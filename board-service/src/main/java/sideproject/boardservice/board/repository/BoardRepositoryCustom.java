package sideproject.boardservice.board.repository;

import org.springframework.data.domain.Page;
import sideproject.boardservice.board.dto.SearchBoardDto;
import sideproject.boardservice.board.dto.projection.BoardSelectorDto;
import sideproject.boardservice.board.dto.projection.SearchBoardPageDto;

import java.util.List;

public interface BoardRepositoryCustom {

    Page<SearchBoardPageDto> searchAll(SearchBoardDto condition);

    List<BoardSelectorDto> boardSelector();
}
