package subproject.admin.board.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import subproject.admin.board.dto.record.*;
import subproject.admin.board.dto.request.SearchBoardRequest;
import subproject.admin.board.dto.request.RegisterBoardRequest;
import subproject.admin.board.dto.request.UpdateBoardRequest;
import subproject.admin.board.dto.response.BoardPageResponse;
import subproject.admin.board.dto.response.RegisterBoardResponse;
import subproject.admin.board.dto.response.SearchBoardResponse;
import subproject.admin.board.dto.response.UpdateBoardResponse;
import subproject.admin.board.entity.Board;
import subproject.admin.board.service.BoardService;
import subproject.admin.common.dto.Result;
import subproject.admin.common.dto.ResultHandler;
import subproject.admin.jwt.principal.PrincipalDetails;

import java.util.Map;
import java.util.UUID;
import java.util.stream.IntStream;

@RestController
@NoArgsConstructor
@RequestMapping("/api/v1/")
public class BoardController {

    @Autowired
    private BoardService boardService;


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/board/listTest")
    public ResponseEntity boardList(PrincipalDetails principalDetails) {
        System.out.println("principalDetails = " + principalDetails);

        return ResponseEntity.ok().body(ResultHandler.handle(HttpStatus.OK.value(), "게시판목록",
                IntStream.rangeClosed(1, 10)
                        .mapToObj(range -> Map.of(
                                "id", range,
                                "title", "제목" + range,
                                "content", "내용" + range
                        ))
                        .toList()
                ));
    }

    @GetMapping("/board")
    public ResponseEntity<Result> searchBoard(@Valid SearchBoardRequest request) {
        BoardPageResponse boardPageResponse = boardService.findAll(SearchBoardDto.from(request));
        return ResponseEntity.ok().body(ResultHandler.handle(
                HttpStatus.OK.value(), "게시판관리 목록", boardPageResponse));
    }

    @GetMapping("/board/{id}")
    public ResponseEntity<Result> selectBoard(@PathVariable Long id) {
        SearchBoardResponse searchBoardResponse = boardService.findById(DetailBoardDto.from(id));
        return ResponseEntity.ok().body(ResultHandler.handle(
                HttpStatus.OK.value(), "게시판관리 상세정보", searchBoardResponse));
    }

    @PostMapping("/board/register")
    public ResponseEntity<Result> insertBoard(@Valid RegisterBoardRequest request) {
        RegisterBoardResponse registerBoardResponse = boardService.save(RegisterBoardDto.from(request));
        return ResponseEntity.ok().body(ResultHandler.handle(
                HttpStatus.OK.value(), "게시판관리 등록", registerBoardResponse
        ));
    }

    @PutMapping("/board/{id}/update")
    public ResponseEntity<Result> updateBoard(@PathVariable Long id, UpdateBoardRequest request) {
        UpdateBoardResponse updateBoardResponse = boardService.updateById(UpdateBoardDto.of(id, request));
        return ResponseEntity.ok().body(ResultHandler.handle(
                HttpStatus.OK.value(), "게시판관리 수정", updateBoardResponse
        ));
    }

    @DeleteMapping("/board/{id}delete")
    public ResponseEntity<Result> deleteBoard(@PathVariable Long id) {
        boardService.deleteById(DeleteBoardDto.of(id));
        return ResponseEntity.ok().body(ResultHandler.handle(
                HttpStatus.OK.value(), "게시판관리 삭제", ""
        ));
    }

    @PutMapping("/board/{id}/category/{categoryId}/update")
    public ResponseEntity<Result> updateCategory(@PathVariable Long id,
                                                 @PathVariable UUID categoryId,
                                                 @RequestBody String categoryName) {
        boardService.updateCategoryById(UpdateBoardCategoryDto.of(id, categoryId, categoryName));
        return null;
    }

    @DeleteMapping("/board/{id}/category/{categoryId}/delete")
    public ResponseEntity<Result> deleteCategory(@PathVariable Long id, @PathVariable UUID categoryId) {
        boardService.deleteCategoryById(DeleteBoardCategoryDto.of(id, categoryId));
        return null;
    }
}
