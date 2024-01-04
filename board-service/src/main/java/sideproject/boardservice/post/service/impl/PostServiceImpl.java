package sideproject.boardservice.post.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sideproject.boardservice.board.entity.Board;
import sideproject.boardservice.board.entity.BoardCategory;
import sideproject.boardservice.board.repository.BoardCategoryRepository;
import sideproject.boardservice.board.repository.BoardRepository;
import sideproject.boardservice.common.dto.FileDto;
import sideproject.boardservice.common.exception.UserInformationNotMatchException;
import sideproject.boardservice.feign.FileServiceClient;
import sideproject.boardservice.post.dto.*;
import sideproject.boardservice.post.dto.item.RegisterPostItem;
import sideproject.boardservice.post.dto.item.SearchPostItem;
import sideproject.boardservice.post.dto.item.SelectPostItem;
import sideproject.boardservice.post.dto.item.UpdatePostItem;
import sideproject.boardservice.post.dto.response.RegisterPostResponse;
import sideproject.boardservice.post.dto.response.SearchPostResponse;
import sideproject.boardservice.post.dto.response.SelectPostResponse;
import sideproject.boardservice.post.dto.response.UpdatePostResponse;
import sideproject.boardservice.post.entity.Post;
import sideproject.boardservice.post.projection.SearchPostPageDto;
import sideproject.boardservice.post.repository.PostRepository;
import sideproject.boardservice.post.repository.PostRepositoryCustom;
import sideproject.boardservice.post.service.PostService;

import java.util.List;
import java.util.Optional;
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
    @Override
    @Transactional
    public RegisterPostResponse save(RegisterPostDto dto, MultipartHttpServletRequest request) {
        Board board = boardRepository.findById(dto.boardId())
                .orElseThrow(EntityNotFoundException::new);
        BoardCategory boardCategory = boardCategoryRepository.findById(dto.categoryId())
                .orElseThrow(EntityNotFoundException::new);
        Post post = Post.createPost(dto, board, boardCategory);
            Optional.ofNullable(request.getMultiFileMap())
                    .ifPresent(file -> {
                        ResponseEntity<UUID> responseEntity = fileServiceClient.fileRegister(request);
                        UUID fileMappingId = responseEntity.getBody();
                        post.addFileMappingId(fileMappingId);
                    });
        Post savePost = postRepository.save(post);
        RegisterPostItem registerPostItem = RegisterPostItem.postEntityToDto(savePost);
        return new RegisterPostResponse(registerPostItem);
    }

    @Override
    @Transactional(readOnly = true)
    public SelectPostResponse selectPost(SelectPostDto dto) {
        Post post = postRepository.findById(dto.id())
                .orElseThrow(EntityNotFoundException::new);
        SelectPostItem selectPostItem = SelectPostItem.postEntityToDto(post);
        Optional.ofNullable(post.getFileMappingId())
                        .ifPresent(id -> {
                            List<FileDto> files = fileServiceClient.files(id).getBody();
                            selectPostItem.addFiles(files);
                        });
        return new SelectPostResponse(selectPostItem);
    }

    @Override
    @Transactional(readOnly = true)
    public SearchPostResponse findAll(SearchPostDto dto) {
        Page<SearchPostPageDto> searchPostPageDtos = postRepositoryCustom.searchAll(dto);
        searchPostPageDtos.getContent();
        searchPostPageDtos.getPageable();
        SearchPostItem searchPostItem = SearchPostItem.postEntityToDto(searchPostPageDtos);
        return new SearchPostResponse(searchPostItem);
    }

    @Override
    @Transactional
    public UpdatePostResponse updatePost(UpdatePostDto dto, MultipartHttpServletRequest request, UserDetails userDetails) {
        Post post = postRepository.findById(dto.postId())
                .orElseThrow(EntityNotFoundException::new);
        usernameMatch(userDetails.getUsername(), post.getCreatedBy());
        post.updatePost(dto);
        Optional.ofNullable(request.getMultiFileMap())
                .ifPresent(map -> {
                    Optional.ofNullable(post.getFileMappingId())
                            .ifPresentOrElse(uuid -> {
                                fileServiceClient.fileUpdate(request, uuid);
                            }, () -> {
                                UUID responseFileMappingId = fileServiceClient.fileRegister(request).getBody();
                                post.addFileMappingId(responseFileMappingId);
                            });
                });
        UpdatePostItem updatePostItem = UpdatePostItem.postEntityToDto(post);
        return new UpdatePostResponse(updatePostItem);
    }

    @Override
    @Transactional
    public void deletePost(DeletePostDto dto, UserDetails userDetails) {
        Post post = postRepository.findById(dto.postId())
                .orElseThrow(EntityNotFoundException::new);
        usernameMatch(post.getCreatedBy(), userDetails.getUsername());
        Optional.ofNullable(post.getFileMappingId())
                .ifPresent(uuid -> {
                    fileServiceClient.fileMappingDelete(uuid);
                });
        postRepository.delete(post);
    }

    private void usernameMatch(final String createdBy, final String username) {
        if(Boolean.FALSE.equals(username.equals(createdBy))) {
            throw new UserInformationNotMatchException();
        }
    }
}
