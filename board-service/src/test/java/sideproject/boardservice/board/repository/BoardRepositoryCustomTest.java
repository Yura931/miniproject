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
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import sideproject.boardservice.board.TestBoardData;
import sideproject.boardservice.board.dto.SearchBoardDto;
import sideproject.boardservice.board.dto.enums.BoardSortCondition;
import sideproject.boardservice.board.dto.projection.SearchBoardPageDto;
import sideproject.boardservice.common.enums.SortDirection;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
@Rollback(value = false)
class BoardRepositoryCustomTest {

    @Autowired
    BoardRepositoryCustom boardRepositoryCustom;

    @Autowired
    BoardRepository boardRepository;
    @PersistenceContext
    EntityManager em;
    @Autowired
    TestBoardData testBoardData;

    @BeforeEach
    void beforeEach() {
        testBoardData.boardData(30)
                .stream().map(board -> boardRepository.save(board))
                .toList()
                .stream()
                .findFirst()
                .ifPresent(board -> testBoardData.setFirstData(testBoardData.getKey(), board));

        em.flush();
        em.clear();
    }

    @Test
    @DisplayName("전체 조회 테스트")
    void searchAllTest() {
        SearchBoardDto searchBoardDto = SearchBoardDto.of(SortDirection.DESC, BoardSortCondition.CREATE_AT, 0, 10);
        Page<SearchBoardPageDto> searchBoardPage = boardRepositoryCustom.searchAll(searchBoardDto);

        int totalPages = searchBoardPage.getTotalPages();
        long totalElements = searchBoardPage.getTotalElements();
        int size = searchBoardPage.getSize();
        List<SearchBoardPageDto> contents = searchBoardPage.getContent();

        Assertions.assertAll(() -> {
            assertThat(totalPages).isEqualTo(3);
            assertThat(totalElements).isEqualTo(30);
            assertThat(size).isEqualTo(10);
        });

        SearchBoardPageDto searchBoardPageDto = contents.get(0);
        String boardTitle = searchBoardPageDto.getBoardTitle();
        assertThat(boardTitle).isEqualTo("title30");
    }

}