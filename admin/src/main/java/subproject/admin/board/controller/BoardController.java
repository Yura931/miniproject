package subproject.admin.board.controller;

import com.amazonaws.Response;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import subproject.admin.board.dto.item.BoardEnumsItem;
import subproject.admin.board.dto.record.*;
import subproject.admin.board.dto.request.*;
import subproject.admin.board.dto.response.*;
import subproject.admin.board.entity.enums.BoardType;
import subproject.admin.board.entity.enums.Enabled;
import subproject.admin.board.service.BoardService;
import subproject.admin.common.dto.Result;
import subproject.admin.common.dto.ResultHandler;
import subproject.admin.common.enums.EnumDto;
import subproject.admin.common.enums.SortDirection;
import subproject.admin.jwt.principal.PrincipalDetails;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.IntStream;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class BoardController {

    private final BoardService boardService;

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
    public ResponseEntity<Result> searchBoard(@Valid SearchBoardRequest request, HttpServletRequest req) {
        System.out.println("request = " + request);
        Map<String, String[]> parameterMap = req.getParameterMap();
        parameterMap.entrySet().stream()
                .map(Map.Entry::getValue)
                .forEach((value) -> System.out.println(Arrays.toString(value)));

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
