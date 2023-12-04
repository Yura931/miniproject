package subproject.admin.board.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import subproject.admin.board.dto.RegisterBoardDto;
import subproject.admin.board.dto.request.RegisterBoardRequest;
import subproject.admin.board.entity.Board;
import subproject.admin.board.entity.enums.Enabled;

import java.util.*;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Rollback(value = false)
@Transactional
class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;
    @PersistenceContext
    EntityManager em;

    Map<String, UUID> initIdMap = new HashMap<>();

    @BeforeEach
    public void beforeEach() {
        IntStream.rangeClosed(1, 2)
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

        em.createQuery("select b from Board b", Board.class).getResultList()
                .stream()
                .findFirst()
                .ifPresent((Board b) -> { initIdMap.put("firstId", b.getId()); });

    }

    @Test
    public void boardData() {
        List<Board> bm = em.createQuery("select bm from Board bm", Board.class)
                .getResultList();

        bm.stream()
                .forEach(e -> System.out.println("board = " + e.getBoardCategory().getCategories()));
    }

    @Test
    public void boardFindById() {
        UUID firstId = initIdMap.get("firstId");
        Board board = boardRepository.findById(firstId).get();
        assertThat(board.getId()).isEqualTo(firstId);
    }

    @Test
    public void boardPageableTest() {
        PageRequest page = PageRequest.of(0, 10, Sort.by("createDate").descending());
        Page<Board> all = boardRepository.findAll(page);
        assertThat(all).size().isEqualTo(10);
    }
}