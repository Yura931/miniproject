package sideproject.boardservice.post.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sideproject.boardservice.common.dto.Result;
import sideproject.boardservice.common.dto.ResultHandler;
import sideproject.boardservice.post.dto.RegisterPostDto;
import sideproject.boardservice.post.dto.SearchPostDto;
import sideproject.boardservice.post.dto.request.RegisterPostRequest;
import sideproject.boardservice.post.dto.request.SearchPostRequest;
import sideproject.boardservice.post.dto.response.SearchPostResponse;
import sideproject.boardservice.post.service.PostService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/posts")
    public ResponseEntity<Result> searchPosts(@Valid SearchPostRequest request) {
        SearchPostDto dto = SearchPostDto.of(request.getBoardId(), request, PageRequest.of(request.getPageNo(), request.getPageSize()));
        SearchPostResponse searchPosts = postService.findAll(dto);
        return ResponseEntity.ok().body(ResultHandler.handle(HttpStatus.OK.value(), "게시글 목록", searchPosts));
    }

    @GetMapping("/board/{boardId}/posts")
    public ResponseEntity<Result> searchBoardPosts(@PathVariable Long boardId) {
        return null;
    }

    @PostMapping("/board/{boardId}/posts/register")
    public ResponseEntity<Result> registerPost(MultipartHttpServletRequest multipartRequest, @PathVariable Long boardId, RegisterPostRequest request) throws Exception {
        RegisterPostDto dto = RegisterPostDto.of(boardId, request,0L);
        postService.save(dto, multipartRequest);
        return null;
    }

    @PutMapping("/posts/{postId}/update")
    public ResponseEntity<Result> updatePost(@PathVariable UUID postId) {
        return null;
    }

    @DeleteMapping("/posts/{postId}/delete")
    public ResponseEntity<Result> deletePost(@PathVariable UUID postId) {
        return null;
    }
}
