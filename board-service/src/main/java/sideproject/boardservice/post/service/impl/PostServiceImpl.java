package sideproject.boardservice.post.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.data.domain.Page;
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
import sideproject.boardservice.messagequeue.PostProducer;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {
    private final BoardRepository boardRepository;
    private final PostRepository postRepository;
    private final PostRepositoryCustom postRepositoryCustom;
    private final BoardCategoryRepository boardCategoryRepository;
    private final FileServiceClient fileServiceClient;
    private final PostProducer postProducer;
    private final CircuitBreakerFactory circuitBreakerFactory;

    @Override
    @Transactional
    public RegisterPostResponse save(RegisterPostDto dto, MultipartHttpServletRequest request) {
        Board board = boardRepository.findById(dto.boardId())
                .orElseThrow(EntityNotFoundException::new);
        Post post = Post.createPost(dto, board);
        Optional.ofNullable(dto.categoryId())
                .ifPresent(categoryId -> {
                    BoardCategory boardCategory = boardCategoryRepository.findById(categoryId)
                            .orElseThrow(EntityNotFoundException::new);
                    post.addBoardCategory(boardCategory);
                });
        Optional.ofNullable(request.getMultiFileMap())
                .ifPresent(multiValueMap -> {
                    UUID fileMappingId = fileServiceClient.fileRegister(
                            multiValueMap.values().stream().flatMap(List::stream)
                                    .collect(Collectors.toList())
                    ).getBody();
                    post.addFileMappingId(fileMappingId);
                });
        Post savePost = postRepository.save(post);
//        postProducer.send("posts", KafkaRegisterPostDto.postEntityToDto(post));
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
    public UpdatePostResponse updatePost(UpdatePostDto dto, MultipartHttpServletRequest request) {
        Post post = postRepository.findById(dto.postId())
                .orElseThrow(EntityNotFoundException::new);
        post.updatePost(dto);
        UpdatePostItem updatePostItem = UpdatePostItem.postEntityToDto(post);
        return new UpdatePostResponse(updatePostItem);
    }

    @Override
    @Transactional
    public void deletePost(DeletePostDto dto) {
        Post post = postRepository.findById(dto.postId())
                .orElseThrow(EntityNotFoundException::new);
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
