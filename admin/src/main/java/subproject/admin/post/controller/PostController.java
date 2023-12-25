package subproject.admin.post.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import subproject.admin.common.dto.Result;
import subproject.admin.common.dto.ResultHandler;
import subproject.admin.file.dto.FileDto;
import subproject.admin.file.util.FileUtil;
import subproject.admin.post.dto.record.RegisterPostDto;
import subproject.admin.post.dto.record.SearchPostDto;
import subproject.admin.post.dto.request.RegisterPostRequest;
import subproject.admin.post.dto.request.SearchPostRequest;
import subproject.admin.post.dto.response.SearchPostResponse;
import subproject.admin.post.projection.SearchPostPageDto;
import subproject.admin.post.service.PostService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class PostController {

    private final FileUtil fileUtil;
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
        List<FileDto> fileDtos = fileUtil.uploadFileDto(multipartRequest);
        RegisterPostDto dto = RegisterPostDto.of(boardId, request,0L, fileDtos);
        postService.save(dto);
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
