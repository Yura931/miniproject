package sideproject.boardservice.post.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import sideproject.boardservice.TestData;
import sideproject.boardservice.board.entity.Board;
import sideproject.boardservice.board.entity.BoardCategory;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PostTest {

    @PersistenceContext
    EntityManager em;

    TestData<Post> postData = new TestData<>();
    TestData<Board> boardData = new TestData<>();

    @BeforeEach
    void beforeEach() {
        Board board = boardData.boards(1).get(0);
        boardData.setFirstData(boardData.getKey(), board);
        em.persist(board);
        em.flush();
        em.clear();

        postData.posts(10, board)
                .stream().map(post -> {
                    board.getCategories().stream().findFirst().ifPresent(boardCategory -> post.addBoardCategory(boardCategory));
                    em.persist(post);
                    return post;
                })
                .toList()
                .stream()
                .findFirst()
                .ifPresent(post -> postData.setFirstData(postData.getKey(), post));

        em.flush();
        em.clear();
    }

    @Test
    @DisplayName("해당 게시판 게시물 전체 조회 테스트")
    void postSelectTest() {
        List<Post> posts = em.createQuery("select p from Post p where p.board.id = :id", Post.class)
                .setParameter("id", boardData.getFirstData().get(boardData.getKey()).getId())
                .getResultList();

        BoardCategory boardCategory = posts.get(0).getBoardCategory();
        assertThat(posts).size().isEqualTo(10);
        assertThat(boardCategory).isNotNull();
    }

    @Test
    @DisplayName("게시물 조회수 테스트")
    void updateViewCountTest() {
        Post post = em.find(Post.class, postData.getFirstData().get(postData.getKey()).getId());
        post.updateViewCount();

        assertThat(post.getViewCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("")
    void unixTimeTest() {
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(System.currentTimeMillis() / 1000), ZoneId.systemDefault());
        System.out.println("dateTime = " + dateTime);
    }
}