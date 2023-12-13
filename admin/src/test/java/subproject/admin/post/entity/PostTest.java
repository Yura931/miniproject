package subproject.admin.post.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import subproject.admin.board.dto.record.BoardCategoryDto;
import subproject.admin.board.dto.record.RegisterBoardDto;
import subproject.admin.board.dto.request.RegisterBoardRequest;
import subproject.admin.board.entity.Board;
import subproject.admin.board.entity.BoardCategory;
import subproject.admin.board.entity.enums.BoardType;
import subproject.admin.board.entity.enums.Enabled;
import subproject.admin.board.repository.BoardRepository;
import subproject.admin.post.dto.record.RegisterPostDto;
import subproject.admin.post.repository.PostRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

@SpringBootTest
@Rollback(value = false)
@Transactional
class PostTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private PostRepository postRepository;

    Map<String, Board> initMap = new HashMap<>();

    @PersistenceContext
    EntityManager em;

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
        initMap.put("saveBoard", save);

        IntStream.rangeClosed(1, 10)
                .forEach(value -> {
                    BoardCategory boardCategory = BoardCategory.createCategory(save, BoardCategoryDto.from(save.getCategories().get(0).getCategoryName()));
                    RegisterPostDto of = RegisterPostDto.of(save, boardCategory, "postTitle"+value, "postContent"+value, new ArrayList<>());
                    Post post = Post.createPost(of, new ArrayList<>());
                    em.persist(post);
                });
    }

    @Test
    public void registerPostTest() {
        Board saveBoard = initMap.get("saveBoard");
        BoardCategory boardCategory = saveBoard.getCategories().get(0);
        RegisterPostDto of = RegisterPostDto.of(saveBoard, boardCategory, "postTitle", "postContent", new ArrayList<>());
        Post post = Post.createPost(of, new ArrayList<>());
        Post save = postRepository.save(post);

        Assertions.assertThat(save.getId()).isEqualTo(post.getId());
    }

    @Test
    public void postPageableList() {
        Board saveBoard = initMap.get("saveBoard");
        PageRequest of = PageRequest.of(0, 10);
        Page<Post> allByBoard = postRepository.findAllByBoard(of, saveBoard);
        Assertions.assertThat(allByBoard).size().isEqualTo(10);
    }

}