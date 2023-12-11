package subproject.admin.board.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import subproject.admin.board.dto.SearchBoardDto;
import subproject.admin.board.dto.RegisterBoardDto;
import subproject.admin.board.dto.DetailBoardDto;
import subproject.admin.board.dto.request.SearchBoardRequest;
import subproject.admin.board.dto.request.RegisterBoardRequest;
import subproject.admin.board.dto.response.BoardPageResponse;
import subproject.admin.board.dto.response.RegisterBoardResponse;
import subproject.admin.board.dto.response.SearchBoardResponse;
import subproject.admin.board.service.BoardService;
import subproject.admin.common.dto.Result;
import subproject.admin.common.dto.ResultHandler;
import subproject.admin.jwt.principal.PrincipalDetails;

import java.util.Map;
import java.util.UUID;
import java.util.stream.IntStream;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
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
    public ResponseEntity<Result> getBoardList(@Valid SearchBoardRequest request) {
        BoardPageResponse page = boardService.findAll(SearchBoardDto.from(request));
        return ResponseEntity.ok().body(ResultHandler.handle(
                HttpStatus.OK.value(), "게시판관리 목록", page));
    }

    @GetMapping("/board/{id}")
    public ResponseEntity<Result> getBoardDetail(@PathVariable UUID id) {
        SearchBoardResponse byId = boardService.findById(DetailBoardDto.from(id));
        return ResponseEntity.ok().body(ResultHandler.handle(
                HttpStatus.OK.value(), "게시판관리 상세정보", byId));
    }

    @PostMapping("/board/register")
    public ResponseEntity<Result> registerBoard(@Valid RegisterBoardRequest request) {
        RegisterBoardResponse save = boardService.save(RegisterBoardDto.from(request));
        return ResponseEntity.ok().body(ResultHandler.handle(
                HttpStatus.OK.value(), "게시판관리 등록", save
        ));
    }

    @PutMapping("/board/update")
    public ResponseEntity<Result> updateBoard() {
        return null;
    }

    @DeleteMapping("/board/delete")
    public ResponseEntity<Result> deleteBoard() {
        return null;
    }


}
