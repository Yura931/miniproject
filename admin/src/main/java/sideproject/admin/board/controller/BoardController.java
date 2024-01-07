package sideproject.admin.board.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sideproject.admin.board.dto.request.*;
import sideproject.admin.common.dto.Result;
import sideproject.admin.common.dto.ResultHandler;
import sideproject.admin.feign.BoardServiceClient;
import sideproject.admin.jwt.principal.PrincipalDetails;

import java.util.Map;
import java.util.UUID;
import java.util.stream.IntStream;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class BoardController {

    private final BoardServiceClient boardServiceClient;

    @GetMapping("/board/board-service-test")
    public ResponseEntity<String> test() {
        log.info("board-service-test ===========");
        return boardServiceClient.test();
    }

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
        return boardServiceClient.searchBoard(request);
    }

    @GetMapping("/board/enums")
    public ResponseEntity<Result> enums() {
        return boardServiceClient.enums();
    }

    @GetMapping("/board/{boardId}")
    public ResponseEntity<Result> selectBoard(@PathVariable Long boardId) {
        return boardServiceClient.selectBoard(boardId);
    }

    @PostMapping("/board/register")
    public ResponseEntity<Result> insertBoard(@Valid @RequestBody RegisterBoardRequest request) {
        return boardServiceClient.insertBoard(request);
    }

    @PutMapping("/board/{boardId}")
    public ResponseEntity<Result> updateBoard(@PathVariable Long boardId, @Valid @RequestBody UpdateBoardRequest request) {
        return boardServiceClient.updateBoard(boardId, request);
    }

    @DeleteMapping("/board/{boardId}")
    public ResponseEntity<Result> deleteBoard(@PathVariable Long boardId) {
        return boardServiceClient.deleteBoard(boardId);
    }

    @PostMapping("/board/{boardId}/category/register")
    public ResponseEntity<Result> insertCategory(@PathVariable Long boardId, @Valid @RequestBody RegisterBoardCategoryRequest request) {
        return boardServiceClient.insertCategory(boardId, request);
    }

    @PutMapping("/board/{boardId}/category/{categoryId}")
    public ResponseEntity<Result> updateCategory(@PathVariable Long boardId,
                                                 @PathVariable UUID categoryId,
                                                 @Valid @RequestBody UpdateBoardCategoryRequest request) {
        return boardServiceClient.updateCategory(boardId, categoryId, request);
    }

    @DeleteMapping("/board/{boardId}/category/{categoryId}")
    public ResponseEntity<Result> deleteCategory(@PathVariable Long boardId, @PathVariable UUID categoryId) {
        return boardServiceClient.deleteCategory(boardId, categoryId);
    }
}
