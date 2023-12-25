package subproject.admin.board.repository;

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
import subproject.admin.board.dto.record.SearchBoardDto;
import subproject.admin.board.dto.enums.BoardSortCondition;
import subproject.admin.board.dto.projection.SearchBoardPageDto;
import subproject.admin.board.dto.request.RegisterBoardRequest;
import subproject.admin.board.entity.Board;
import subproject.admin.board.entity.enums.BoardType;
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
                    boardRepository.save(board);
                });

    }

    @Test
    public void queryDslPageTest() {

        Page<SearchBoardPageDto> searchBoardPageDtos = boardRepositoryCustom.searchAll(
                SearchBoardDto.of(
                        "title",
                        "content",
                        SortDirection.ASC,
                        BoardSortCondition.CREATE_AT,
                        0,
                        10)
        );

        List<SearchBoardPageDto> content = searchBoardPageDtos.getContent();
        Pageable pageable = searchBoardPageDtos.getPageable();
        System.out.println("pageable = " + pageable);

    }

}