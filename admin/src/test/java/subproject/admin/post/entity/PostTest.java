package subproject.admin.post.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
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
import subproject.admin.board.repository.BoardCategoryRepository;
import subproject.admin.board.repository.BoardRepository;
import subproject.admin.post.dto.record.RegisterPostDto;
import subproject.admin.post.repository.PostRepository;
import subproject.admin.post.repository.PostRepositoryCustom;

import java.util.*;
import java.util.stream.IntStream;

@SpringBootTest
@Rollback(value = false)
@Transactional
class PostTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostRepositoryCustom postRepositoryCustom;

    @Autowired
    private BoardCategoryRepository boardCategoryRepository;
    Map<String, Board> initMap = new HashMap<>();
    Map<String, Post> initPostMap = new HashMap<>();

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

/*        IntStream.rangeClosed(1, 2)
                .forEach(value -> {
                    UUID categoryId = save.getCategories().get(value - 1).getId();
                    RegisterPostDto of = RegisterPostDto.of(save.getId(), categoryId, "postTitle"+value, "postContent"+value, 0L, new ArrayList<>());
                    Post post = Post.createPost(of, save);
                    em.persist(post);
                    initPostMap.put("savePost" + value, post);
                });

        em.flush();
        em.clear();
        initMap.put("saveBoard", save);*/
    }

    @Test
    public void registerPostTest() {

    }

    @Test
    public void postPageableList() {
        initPostMap.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .forEach((post) -> {
                    System.out.println("post.getBoardCategory().getCategoryName() = " + post.getBoardCategory().getCategoryName());
                });
    }

}