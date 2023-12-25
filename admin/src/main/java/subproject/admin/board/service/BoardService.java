package subproject.admin.board.service;

import subproject.admin.board.dto.record.*;
import subproject.admin.board.dto.response.*;

public interface BoardService {

    RegisterBoardResponse save(RegisterBoardDto dto);
    SearchBoardResponse findById(SelectBoardDto dto);
    BoardPageResponse findAll(SearchBoardDto dto);
    UpdateBoardResponse updateById(UpdateBoardDto dto);
    void deleteById(DeleteBoardDto dto);
    RegisterBoardResponse insertCategoryByBoardId(RegisterBoardCategoryDto of);
    UpdateBoardResponse updateCategoryByBoardId(UpdateBoardCategoryDto dto);
    DeleteBoardResponse deleteCategoryById(DeleteBoardCategoryDto of);
}
