package sideproject.boardservice.board.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import sideproject.boardservice.board.TestBoardData;
import sideproject.boardservice.board.dto.BoardCategoryDto;
import sideproject.boardservice.board.dto.UpdateBoardDto;
import sideproject.boardservice.board.dto.request.UpdateBoardRequest;
import sideproject.boardservice.board.entity.enums.*;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BoardTest {

    @PersistenceContext
    EntityManager em;

    TestBoardData testBoardData = new TestBoardData();


    @BeforeEach
    void beforeEach() {
        testBoardData.boardData(10)
                .forEach(board -> em.persist(board));
        em.flush();
        em.clear();
    }

    @Test
    @DisplayName("")
    void beforeTest() {

    }


    @Test
    @DisplayName("BOARD 저장 테스트")
    void boardCreateTest() {
        Board board = testBoardData.boardData(1).get(0);
        em.persist(board);

        Board findBoard = em.find(Board.class, board.getId());
        assertThat(board.getId()).isEqualTo(findBoard.getId());
    }

    @Test
    @DisplayName("BOARD 조회 테스트")
    void boardSelectTest() {
        List<Board> boardList = em.createQuery("select b from Board b", Board.class).getResultList();
        assertThat(boardList).size().isEqualTo(10);
        assertThat(boardList).isNotNull();
        assertThat(boardList).size().isGreaterThan(0);
    }

    @Test
    @DisplayName("BOARD 수정 테스트")
    void BoardTest() {
        Board board = em.createQuery("select b from Board b", Board.class)
                .setMaxResults(1)
                .getSingleResult();

        UpdateBoardRequest updateBoardRequest = new UpdateBoardRequest(
                BoardEnabled.Y,
                BoardVisible.N,
                BoardType.GALLERY,
                "title Modified",
                "description Modified",
                BoardCategoryEnabled.Y,
                BoardFileEnabled.Y,
                BoardCommentEnabled.Y
        );

        UpdateBoardDto updateBoardDto = UpdateBoardDto.of(board.getId(), updateBoardRequest);
        board.updateBoard(updateBoardDto);

        assertThat(board.getBoardTitle()).isEqualTo(updateBoardRequest.getBoardTitle());
    }
    
    @Test
    @DisplayName("BOARD 삭제 테스트")
    void boardDeleteTest() {
        Board findBoard = em.createQuery("select b from Board b", Board.class)
                .setMaxResults(1)
                .getSingleResult();

        em.remove(findBoard);
        List<Board> findBoardList = em.createQuery("select b from Board b", Board.class).getResultList();
        assertThat(findBoardList).size().isEqualTo(9);
    }
    
    @Test
    @DisplayName("카테고리 추가 테스트")
    void addBoardCategoryTest() {
        Board findBoard = em.createQuery("select b from Board b", Board.class)
                .setMaxResults(1)
                .getSingleResult();

        BoardCategory newCategory = BoardCategory.createCategory(findBoard, BoardCategoryDto.from("add category"));
        findBoard.addBoardCategory(newCategory);

        assertThat(findBoard.getCategories()).size().isEqualTo(3);
    }

    @Test
    @DisplayName("카테고리 삭제 테스트")
    void deleteBoardCategoryTest() {
        Board findBoard = em.createQuery("select b from Board b", Board.class)
                .setMaxResults(1)
                .getSingleResult();

        UUID categoryId = findBoard.getCategories().get(0).getId();
        findBoard.deleteBoardCategory(categoryId);
        assertThat(findBoard.getCategories()).size().isEqualTo(1);
    }

    @Test
    @DisplayName("카테고리 수정 테스트")
    void updateBoardCategoryTest() {
        final String updateCategoryName = "update category";
        Board findBoard = em.createQuery("select b from Board b", Board.class)
                .setMaxResults(1)
                .getSingleResult();

        BoardCategory boardCategory = findBoard.getCategories().get(0);
        boardCategory.updateCategory(updateCategoryName);

        assertThat(findBoard.getCategories().get(0).getCategoryName()).isEqualTo(updateCategoryName);
    }
}