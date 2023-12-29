package sideproject.boardservice.post.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sideproject.boardservice.board.entity.Board;
import sideproject.boardservice.board.entity.BoardCategory;
import sideproject.boardservice.board.repository.BoardCategoryRepository;
import sideproject.boardservice.board.repository.BoardRepository;
import sideproject.boardservice.feign.FileServiceClient;
import sideproject.boardservice.post.dto.RegisterPostDto;
import sideproject.boardservice.post.dto.SearchPostDto;
import sideproject.boardservice.post.dto.SelectPostDto;
import sideproject.boardservice.post.dto.item.RegisterPostItem;
import sideproject.boardservice.post.dto.item.SearchPostItem;
import sideproject.boardservice.post.dto.item.SelectPostItem;
import sideproject.boardservice.post.dto.response.RegisterPostResponse;
import sideproject.boardservice.post.dto.response.SearchPostResponse;
import sideproject.boardservice.post.dto.response.SelectPostResponse;
import sideproject.boardservice.post.entity.Post;
import sideproject.boardservice.post.projection.SearchPostPageDto;
import sideproject.boardservice.post.repository.PostRepository;
import sideproject.boardservice.post.repository.PostRepositoryCustom;
import sideproject.boardservice.post.service.PostService;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {
    private final BoardRepository boardRepository;
    private final PostRepository postRepository;
    private final PostRepositoryCustom postRepositoryCustom;
    private final BoardCategoryRepository boardCategoryRepository;
    private final FileServiceClient fileServiceClient;
    private final ObjectMapper objectMapper;
    @Override
    @Transactional
    public RegisterPostResponse save(RegisterPostDto dto, MultipartHttpServletRequest request) throws JsonProcessingException {
        Board board = boardRepository.findById(dto.boardId())
                .orElseThrow(EntityNotFoundException::new);
        BoardCategory boardCategory = boardCategoryRepository.findById(dto.categoryId())
                .orElseThrow(EntityNotFoundException::new);
        Post post = Post.createPost(dto, board, boardCategory);
        if (!Objects.isNull(request.getMultiFileMap())) {
            ResponseEntity responseEntity = fileServiceClient.fileRegister(request);
            UUID fileMappingId = UUID.fromString(objectMapper.writeValueAsString(responseEntity.getBody()));
            post.addFileMappingId(fileMappingId);
        }
        Post savePost = postRepository.save(post);
        RegisterPostItem registerPostItem = RegisterPostItem.PostEntityToDto(savePost);
        return new RegisterPostResponse(registerPostItem);
    }

    @Override
    @Transactional(readOnly = true)
    public SelectPostResponse findById(SelectPostDto dto) {
        Post post = postRepository.findById(dto.id())
                .orElseThrow(EntityNotFoundException::new);
        SelectPostItem selectPostItem = SelectPostItem.PostEntityToDto(post);
        return new SelectPostResponse(selectPostItem);
    }

    @Override
    public SearchPostResponse findAll(SearchPostDto dto) {
        Page<SearchPostPageDto> searchPostPageDtos = postRepositoryCustom.searchAll(dto);
        searchPostPageDtos.getContent();
        searchPostPageDtos.getPageable();
        SearchPostItem searchPostItem = SearchPostItem.PostEntityToDto(searchPostPageDtos);
        return new SearchPostResponse(searchPostItem);
    }

}
