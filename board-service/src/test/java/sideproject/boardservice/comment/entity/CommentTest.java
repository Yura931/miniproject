package sideproject.boardservice.comment.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import sideproject.boardservice.TestData;
import sideproject.boardservice.board.entity.Board;
import sideproject.boardservice.post.entity.Post;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CommentTest {

    TestData<Board> boardDate = new TestData<>();
    TestData<Post> postData = new TestData<>();
    TestData<Comment> commentData = new TestData<>();
    TestData<Reply> replyData = new TestData<>();

    @PersistenceContext
    EntityManager em;

    @BeforeEach
    void beforeEach() {
        Board board = boardDate.boards(1).get(0);
        em.persist(board);
        em.flush();
        em.clear();

        Post post = postData.posts(1, board).get(0);
        postData.setFirstData(postData.getKey(), post);
        em.persist(post);
        em.flush();
        em.clear();

        Post post1 = em.find(Post.class, post.getId());
        List<Comment> comments = commentData.comments(2, post1);
        comments.forEach(comment -> em.persist(comment));
        em.flush();
        em.clear();

        List<Comment> findComments = em.createQuery("select c from Comment c", Comment.class)
                .getResultList();
        findComments.forEach(comment -> {
            replyData.replies(10, comment)
                    .forEach(reply -> comment.addReply(reply));
        });

        em.flush();
        em.clear();
    }

    @Test
    @DisplayName("댓글 조회 테스트")
    void commentsTest() {
        Post post = em.createQuery("select p from Post p", Post.class)
                .setMaxResults(1)
                .getSingleResult();

        List<Comment> comments = em.createQuery("select c from Comment c where c.post.id = :id order by c.createDate desc", Comment.class)
                .setParameter("id", post.getId())
                .getResultList();

        assertThat(comments).size().isEqualTo(2);
    }

    @Test
    @DisplayName("댓글 수정 테스트")
    void commentUpdateTest() {
        Comment findComment = em.createQuery("select c from Comment c where c.post.id = :id", Comment.class)
                .setParameter("id", postData.getFirstData().get(postData.getKey()).getId())
                .setMaxResults(1)
                .getSingleResult();

        findComment.updateComment("modified content");

        Comment updateComment = em.find(Comment.class, findComment.getId());
        assertThat(updateComment.getContent()).isEqualTo("modified content");

    }
    @Test
    @DisplayName("대댓글 조회 테스트")
    void repliesTest() {
        Post post = em.createQuery("select p from Post p", Post.class)
                .setMaxResults(1)
                .getSingleResult();

        List<Comment> comments = em.createQuery("select c from Comment c where c.post.id = :id order by c.createDate desc", Comment.class)
                .setParameter("id", post.getId())
                .getResultList();

        List<Reply> replies = comments.get(0).getReplies();
        assertThat(replies).size().isEqualTo(10);
    }
}