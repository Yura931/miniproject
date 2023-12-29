package sideproject.boardservice.board.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sideproject.boardservice.board.dto.*;
import sideproject.boardservice.board.dto.item.BoardEnumsItem;
import sideproject.boardservice.board.dto.request.*;
import sideproject.boardservice.board.dto.response.*;
import sideproject.boardservice.board.service.BoardService;
import sideproject.boardservice.common.dto.Result;
import sideproject.boardservice.common.dto.ResultHandler;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok().body("board-service-test");
    }
    @GetMapping("/board")
    public ResponseEntity<Result> searchBoard(@Valid SearchBoardRequest request) {
        BoardPageResponse boardPageResponse = boardService.findAll(SearchBoardDto.from(request));
        return ResponseEntity.ok().body(ResultHandler.handle(
                HttpStatus.OK.value(), "게시판관리 목록", boardPageResponse));
    }
    @GetMapping("/board/enums")
    public ResponseEntity<Result> enums() {
        BoardEnumsResponse enums = new BoardEnumsResponse(BoardEnumsItem.enums());
        return ResponseEntity.ok().body(ResultHandler.handle(
                HttpStatus.OK.value(), "게시판 ENUMS", enums));
    }

    @GetMapping("/board/{id}")
    public ResponseEntity<Result> selectBoard(@PathVariable Long id) {
        SearchBoardResponse searchBoardResponse = boardService.findById(SelectBoardDto.from(id));
        return ResponseEntity.ok().body(ResultHandler.handle(
                HttpStatus.OK.value(), "게시판관리 상세정보", searchBoardResponse));
    }

    @PostMapping("/board/register")
    public ResponseEntity<Result> insertBoard(@Valid @RequestBody RegisterBoardRequest request) {
        System.out.println("request = " + request);
        RegisterBoardResponse registerBoardResponse = boardService.save(RegisterBoardDto.from(request));
        return ResponseEntity.ok().body(ResultHandler.handle(
                HttpStatus.OK.value(), "게시판관리 등록", registerBoardResponse
        ));
    }

    @PutMapping("/board/{id}")
    public ResponseEntity<Result> updateBoard(@PathVariable Long id, @Valid @RequestBody UpdateBoardRequest request) {
        UpdateBoardResponse updateBoardResponse = boardService.updateById(UpdateBoardDto.of(id, request));
        return ResponseEntity.ok().body(ResultHandler.handle(
                HttpStatus.OK.value(), "게시판관리 수정", updateBoardResponse
        ));
    }

    @DeleteMapping("/board/{id}")
    public ResponseEntity<Result> deleteBoard(@PathVariable Long id) {
        boardService.deleteById(DeleteBoardDto.of(id));
        return ResponseEntity.ok().body(ResultHandler.handle(
                HttpStatus.OK.value(), "게시판관리 삭제", ""
        ));
    }

    @PostMapping("/board/{id}/category/register")
    public ResponseEntity<Result> insertCategory(@PathVariable Long id, @Valid @RequestBody RegisterBoardCategoryRequest request) {
        RegisterBoardResponse registerBoardResponse = boardService.insertCategoryByBoardId(RegisterBoardCategoryDto.of(id, request.getCategoryName()));
        return ResponseEntity.ok().body(ResultHandler.handle(
                HttpStatus.OK.value(), "게시판 카테고리 추가", registerBoardResponse
        ));
    }

    @PutMapping("/board/{id}/category/{categoryId}")
    public ResponseEntity<Result> updateCategory(@PathVariable Long id,
                                                 @PathVariable UUID categoryId,
                                                 @Valid @RequestBody UpdateBoardCategoryRequest request) {
        UpdateBoardResponse updateBoardResponse = boardService.updateCategoryByBoardId(UpdateBoardCategoryDto.of(id, categoryId, request.getCategoryName()));
        return ResponseEntity.ok().body(ResultHandler.handle(
                HttpStatus.OK.value(), "게시판 카테고리 수정", updateBoardResponse
        ));
    }

    @DeleteMapping("/board/{id}/category/{categoryId}")
    public ResponseEntity<Result> deleteCategory(@PathVariable Long id, @PathVariable UUID categoryId) {
        DeleteBoardResponse deleteBoardResponse = boardService.deleteCategoryById(DeleteBoardCategoryDto.of(id, categoryId));
        return ResponseEntity.ok().body(ResultHandler.handle(
                HttpStatus.OK.value(), "게시판 카테고리 삭제", deleteBoardResponse
        ));
    }
}
