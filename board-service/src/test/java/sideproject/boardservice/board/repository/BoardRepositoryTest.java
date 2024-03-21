package sideproject.boardservice.board.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import sideproject.boardservice.TestData;
import sideproject.boardservice.board.entity.Board;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback
class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    BoardRepositoryCustom boardRepositoryCustom;
    @PersistenceContext
    EntityManager em;
    @Autowired
    TestData<Board> testData;
    @BeforeEach
    void beforeEach() {
        testData.boards(10)
                .stream().map(board -> boardRepository.save(board))
                .toList()
                .stream()
                .findFirst()
                .ifPresent(board -> testData.setFirstData(testData.getKey(), board));

        em.flush();
        em.clear();
    }

    @Test
    @DisplayName("저장 테스트")
    void saveTest() {
        Board board = testData.boards(1).get(0);
        Board saveBoard = boardRepository.save(board);

        Board findBoard = boardRepository.findById(saveBoard.getId()).get();
        assertThat(saveBoard.getId()).isEqualTo(findBoard.getId());

    }

    @Test
    @DisplayName("전체 조회 테스트")
    void findAllTest() {
        List<Board> boardFindAll = boardRepository.findAll();
        assertThat(boardFindAll).size().isEqualTo(10);
    }

    @Test
    @DisplayName("페이징 조회 테스트")
    void pageFindAllTest() {

        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Order.desc("id")));
        Page<Board> pageAll = boardRepository.findAll(pageRequest);

        int size = pageAll.getSize();
        int totalPages = pageAll.getTotalPages();
        long totalElements = pageAll.getTotalElements();

        Assertions.assertAll(() -> {
            assertThat(size).isEqualTo(3);
            assertThat(totalPages).isEqualTo(4);
            assertThat(totalElements).isEqualTo(10);
        });
    }

    @Test
    @DisplayName("단일 조회 테스트")
    void findById() {
        Board board = testData.getFirstData().get(testData.getKey());
        Board findBoard = boardRepository.findById(board.getId()).get();

        assertThat(board.getId()).isEqualTo(findBoard.getId());
    }


    @Test
    @DisplayName("전체 삭제 테스트")
    void deleteAllTest() {
        boardRepository.deleteAll();
        List<Board> boardList = boardRepository.findAll();

        assertThat(boardList).size().isEqualTo(0);
    }

    @Test
    @DisplayName("삭제 테스트")
    void deleteTest() {
        Board board = testData.getFirstData().get(testData.getKey());
        boardRepository.delete(board);

        List<Board> boardList = boardRepository.findAll();
        assertThat(boardList).size().isEqualTo(9);
    }

}