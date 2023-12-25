package subproject.admin.post.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import subproject.admin.board.entity.Board;
import subproject.admin.board.entity.BoardCategory;
import subproject.admin.board.repository.BoardCategoryRepository;
import subproject.admin.board.repository.BoardRepository;
import subproject.admin.file.entity.File;
import subproject.admin.file.repository.FileRepository;
import subproject.admin.post.dto.item.SearchPostItem;
import subproject.admin.post.dto.item.SelectPostItem;
import subproject.admin.post.dto.item.RegisterPostItem;
import subproject.admin.post.dto.record.RegisterPostDto;
import subproject.admin.post.dto.record.SearchPostDto;
import subproject.admin.post.dto.record.SelectPostDto;
import subproject.admin.post.dto.response.RegisterPostResponse;
import subproject.admin.post.dto.response.SearchPostResponse;
import subproject.admin.post.dto.response.SelectPostResponse;
import subproject.admin.post.entity.Post;
import subproject.admin.post.projection.SearchPostPageDto;
import subproject.admin.post.repository.PostRepository;
import subproject.admin.post.repository.PostRepositoryCustom;
import subproject.admin.post.service.PostService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {
    private final BoardRepository boardRepository;
    private final PostRepository postRepository;
    private final PostRepositoryCustom postRepositoryCustom;
    private final FileRepository fileRepository;
    private final BoardCategoryRepository boardCategoryRepository;
    @Override
    public RegisterPostResponse save(RegisterPostDto dto) {
        Board board = boardRepository.findById(dto.boardId())
                .orElseThrow(EntityNotFoundException::new);
        BoardCategory boardCategory = boardCategoryRepository.findById(dto.categoryId())
                .orElseThrow(EntityNotFoundException::new);
        Post post = Post.createPost(dto, board, boardCategory);
        List<File> files = File.createFiles(dto.fileDtos())
                .stream()
                .map((file) -> file.addPost(post))
                .toList();
        fileRepository.saveAll(files);
        Post savePost = postRepository.save(post);
        RegisterPostItem registerPostItem = RegisterPostItem.PostEntityToDto(savePost);
        return new RegisterPostResponse(registerPostItem);
    }

    @Override
    public SelectPostResponse findById(SelectPostDto dto) {
        Post post = postRepository.findById(dto.id())
                .orElseThrow(EntityNotFoundException::new);
        List<File> files = fileRepository.findByPost(post);

        SelectPostItem selectPostItem = SelectPostItem.PostEntityToDto(post, files);
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
