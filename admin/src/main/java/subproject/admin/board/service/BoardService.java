package subproject.admin.board.service;

import subproject.admin.board.dto.BoardPageDto;
import subproject.admin.board.dto.RegisterBoardDto;
import subproject.admin.board.dto.SearchBoardDto;
import subproject.admin.board.dto.response.BoardPageResponse;
import subproject.admin.board.dto.response.RegisterBoardResponse;
import subproject.admin.board.dto.response.SearchBoardResponse;

public interface BoardService {

    RegisterBoardResponse save(RegisterBoardDto dto);
    SearchBoardResponse findById(SearchBoardDto dto);
    BoardPageResponse findAll(BoardPageDto dto);
}
