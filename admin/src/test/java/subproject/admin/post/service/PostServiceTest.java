package subproject.admin.post.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import subproject.admin.board.dto.record.RegisterBoardDto;
import subproject.admin.board.dto.request.RegisterBoardRequest;
import subproject.admin.board.entity.Board;
import subproject.admin.board.entity.enums.BoardType;
import subproject.admin.board.entity.enums.Enabled;
import subproject.admin.board.repository.BoardRepository;
import subproject.admin.common.enums.SortDirection;
import subproject.admin.post.dto.record.RegisterPostDto;
import subproject.admin.post.dto.record.SearchPostDto;
import subproject.admin.post.dto.request.RegisterPostRequest;
import subproject.admin.post.dto.request.SearchPostRequest;
import subproject.admin.post.entity.Post;
import subproject.admin.post.projection.SearchPostPageDto;
import subproject.admin.post.repository.PostRepository;
import subproject.admin.post.repository.PostRepositoryCustom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Rollback(value = false)
@Transactional
class PostServiceTest {

    @Autowired
    BoardRepository boardRepository;
    @Autowired
    PostRepository postRepository;

    @Autowired
    PostRepositoryCustom postRepositoryCustom;

    @BeforeEach
    public void beforeEach() {

        RegisterBoardDto registerBoardDto = RegisterBoardDto.from(
                new RegisterBoardRequest(
                        Enabled.Y,
                        Enabled.Y,
                        BoardType.GENERAL,
                        "title",
                        "description",
                        Enabled.Y,
                        Enabled.Y,
                        Enabled.Y,
                        Enabled.Y,
                        Arrays.asList("category1", "category2")
                )
        );
        Board board = Board.createBoard(registerBoardDto);
        Board save = boardRepository.save(board);

//        RegisterPostRequest registerPostRequest = new RegisterPostRequest("title", "content", null);


//        IntStream.range(1, 5)
//                .forEach((value) -> {
//                    Post post = Post.createPost(RegisterPostDto.of(board.getId(), registerPostRequest, 0L, new ArrayList<>()), save);
//                    postRepository.save(post);
//                });
    }

    @Test
    public void postPagingTest() {
        Board board = boardRepository.findById(1L).get();
        Page<SearchPostPageDto> searchPostPageDtos = postRepositoryCustom.searchAll(SearchPostDto.of(board.getId(), SortDirection.ASC, "", "", "", PageRequest.of(0, 10)));
        System.out.println("searchPostPageDtos = " + searchPostPageDtos);
        System.out.println("searchPostPageDtos.getContent() = " + searchPostPageDtos.getContent());

    }


}