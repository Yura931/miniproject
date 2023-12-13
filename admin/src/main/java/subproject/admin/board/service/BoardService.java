package subproject.admin.board.service;

import subproject.admin.board.dto.record.*;
import subproject.admin.board.dto.response.BoardPageResponse;
import subproject.admin.board.dto.response.RegisterBoardResponse;
import subproject.admin.board.dto.response.SearchBoardResponse;
import subproject.admin.board.dto.response.UpdateBoardResponse;

public interface BoardService {

    RegisterBoardResponse save(RegisterBoardDto dto);
    SearchBoardResponse findById(DetailBoardDto dto);
    BoardPageResponse findAll(SearchBoardDto dto);
    UpdateBoardResponse updateById(UpdateBoardDto dto);
    void deleteById(DeleteBoardDto dto);
    UpdateBoardResponse updateCategoryById(UpdateBoardCategoryDto dto);
    void deleteCategoryById(DeleteBoardCategoryDto of);
}
