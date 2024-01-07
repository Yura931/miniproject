package sideproject.admin.feign;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sideproject.admin.board.dto.request.*;
import sideproject.admin.common.dto.Result;

import java.util.UUID;

@FeignClient(name = "board-service")
public interface BoardServiceClient {
    @RequestMapping(value = "/board-service/test", method = RequestMethod.GET)
    ResponseEntity<String> test();

    @GetMapping(value = "/board-service/api/v1/board")
    ResponseEntity<Result> searchBoard(@SpringQueryMap SearchBoardRequest request);
    @GetMapping(value = "/board-service/api/v1/board/enums")
    ResponseEntity<Result> enums();

    @GetMapping(value = "/board-service/api/v1/board/{boardId}")
    ResponseEntity<Result> selectBoard(@PathVariable(value = "boardId") Long boardId);

    @PostMapping(value = "/board-service/api/v1/board/register")
    ResponseEntity<Result> insertBoard(@RequestBody RegisterBoardRequest request);

    @PutMapping(value = "/board-service/api/v1/board/{boardId}")
    ResponseEntity<Result> updateBoard(@PathVariable(value = "boardId") Long boardId, @RequestBody UpdateBoardRequest request);

    @DeleteMapping(value = "/board-service/api/v1/board/{boardId}")
    ResponseEntity<Result> deleteBoard(@PathVariable(value = "boardId") Long boardId);

    @PostMapping(value = "/board-service/api/v1/board/{boardId}/category/register")
    ResponseEntity<Result> insertCategory(@PathVariable(value = "boardId") Long boardId, @RequestBody RegisterBoardCategoryRequest request);

    @PutMapping(value = "/board-service/api/v1/board/{boardId}/category/{categoryId}")
    ResponseEntity<Result> updateCategory(@PathVariable(value = "boardId") Long boardId,
                                          @PathVariable(value = "categoryId") UUID categoryId,
                                          @Valid @RequestBody UpdateBoardCategoryRequest request);


    @DeleteMapping(value = "/board-service/api/v1/board/{boardId}/category/{categoryId}")
    ResponseEntity<Result> deleteCategory(@PathVariable(value = "boardId") Long boardId, @PathVariable(value = "categoryId") UUID categoryId);
}