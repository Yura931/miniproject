package sideproject.boardservice.board.service;

import sideproject.boardservice.board.dto.*;
import sideproject.boardservice.board.dto.response.*;

public interface BoardService {
    BoardSelectorResponse boardSelector();
    RegisterBoardResponse save(RegisterBoardDto dto);
    SearchBoardResponse findById(SelectBoardDto dto);
    BoardPageResponse findAll(SearchBoardDto dto);
    UpdateBoardResponse updateById(UpdateBoardDto dto);
    void deleteById(DeleteBoardDto dto);
    RegisterBoardResponse insertCategoryByBoardId(RegisterBoardCategoryDto of);
    UpdateBoardResponse updateCategoryByBoardId(UpdateBoardCategoryDto dto);
    DeleteBoardResponse deleteCategoryById(DeleteBoardCategoryDto of);
}
