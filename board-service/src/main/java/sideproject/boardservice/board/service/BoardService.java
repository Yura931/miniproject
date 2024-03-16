package sideproject.boardservice.board.service;

import sideproject.boardservice.board.dto.*;
import sideproject.boardservice.board.dto.response.*;

public interface BoardService {
    BoardIdResponse boardId();
    RegisterBoardResponse save(RegisterBoardDto dto);
    SearchBoardResponse findById(SelectBoardDto dto);
    BoardPageResponse findAll(SearchBoardDto dto);
    UpdateBoardResponse updateById(UpdateBoardDto dto);
    void deleteById(DeleteBoardDto dto);
    void insertCategoryByBoardId(RegisterBoardCategoryDto of);
    void updateCategoryByBoardId(UpdateBoardCategoryDto dto);
    void deleteCategoryById(DeleteBoardCategoryDto of);
}
