package sideproject.boardservice.post.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sideproject.boardservice.common.dto.Result;
import sideproject.boardservice.common.dto.ResultHandler;
import sideproject.boardservice.feign.FileServiceClient;
import sideproject.boardservice.post.dto.*;
import sideproject.boardservice.post.dto.request.RegisterPostRequest;
import sideproject.boardservice.post.dto.request.SearchPostRequest;
import sideproject.boardservice.post.dto.request.UpdatePostRequest;
import sideproject.boardservice.post.dto.response.RegisterPostResponse;
import sideproject.boardservice.post.dto.response.SearchPostResponse;
import sideproject.boardservice.post.dto.response.SelectPostResponse;
import sideproject.boardservice.post.dto.response.UpdatePostResponse;
import sideproject.boardservice.post.service.PostService;

import java.util.UUID;

@Controller
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;
    private final FileServiceClient fileServiceClient;

    @GetMapping("/posts")
    public ResponseEntity<Result> searchPosts(@Valid SearchPostRequest request) {
        SearchPostDto dto = SearchPostDto.of(request.getBoardId(), request, PageRequest.of(request.getPageNo(), request.getPageSize()));
        SearchPostResponse searchPostResponse = postService.findAll(dto);
        return ResponseEntity.ok().body(ResultHandler.handle(HttpStatus.OK.value(), "게시글 목록", searchPostResponse));
    }
    @PostMapping(value = "/posts/{boardId}/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Result> registerPost(MultipartHttpServletRequest multipartRequest, @PathVariable Long boardId, RegisterPostRequest request) {
        RegisterPostDto dto = RegisterPostDto.of(boardId, request,0L);
        RegisterPostResponse registerPostResponse = postService.save(dto, multipartRequest);
        return ResponseEntity.ok().body(ResultHandler.handle(HttpStatus.OK.value(), "게시글 등록", registerPostResponse));
    }
    @GetMapping("/posts/{postId}")
    public ResponseEntity<Result> selectPost(@PathVariable UUID postId) {
        SelectPostResponse selectPostResponse = postService.selectPost(SelectPostDto.of(postId));
        return ResponseEntity.ok().body(ResultHandler.handle(HttpStatus.OK.value(), "게시글 상세정보", selectPostResponse));
    }
    @PutMapping(value = "/posts/{postId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Result> updatePost(@PathVariable UUID postId, UpdatePostRequest request, MultipartHttpServletRequest multipartRequest, @AuthenticationPrincipal UserDetails userDetails) {
        UpdatePostResponse updatePostResponse = postService.updatePost(UpdatePostDto.of(postId, request), multipartRequest);
        return ResponseEntity.ok().body(ResultHandler.handle(HttpStatus.OK.value(), "게시글 수정", updatePostResponse));
    }
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<Result> deletePost(@PathVariable UUID postId) {
        postService.deletePost(DeletePostDto.from(postId));
        return ResponseEntity.ok().body(ResultHandler.handle(HttpStatus.OK.value(), "게시글 삭제", ""));
    }
}
