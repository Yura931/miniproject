package subproject.admin.board.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import subproject.admin.board.dto.RegisterBoardDto;
import subproject.admin.board.dto.SearchBoardDto;
import subproject.admin.board.dto.enums.BoardSortCondition;
import subproject.admin.board.dto.projection.SearchBoardPageDto;
import subproject.admin.board.dto.request.RegisterBoardRequest;
import subproject.admin.board.entity.Board;
import subproject.admin.board.entity.enums.Enabled;
import subproject.admin.common.enums.SortDirection;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Rollback(false)
@Transactional
class BoardRepositoryCustomTest {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    BoardRepositoryCustom boardRepositoryCustom;

    @BeforeEach
    public void beforeEach() {

        IntStream.rangeClosed(1, 20)
                .forEach(value -> {
                    RegisterBoardDto registerBoardDto = RegisterBoardDto.from(
                            new RegisterBoardRequest(
                                    Enabled.Y,
                                    Enabled.Y,
                                    "A",
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
                    boardRepository.save(board);
                });

    }

    @Test
    public void queryDslPageTest() {

        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<SearchBoardPageDto> searchBoardPageDtos = boardRepositoryCustom.searchAll(
                SearchBoardDto.of(
                        "title",
                        "content",
                        SortDirection.ASC,
                        BoardSortCondition.CREATE_AT,
                        0,
                        10), pageRequest
        );

        List<SearchBoardPageDto> content = searchBoardPageDtos.getContent();
        System.out.println("content = " + content);

        assertThat(content).size().isEqualTo(10);
    }

}