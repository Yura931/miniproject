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
import subproject.admin.board.dto.record.RegisterBoardDto;
import subproject.admin.board.dto.request.RegisterBoardRequest;
import subproject.admin.board.entity.Board;
import subproject.admin.board.entity.BoardCategory;
import subproject.admin.board.entity.BoardCategoryMapping;
import subproject.admin.board.entity.enums.BoardType;
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

    Map<String, Long> initIdMap = new HashMap<>();

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
                .forEach(e -> System.out.println("board = " + e.getBoardCategoryMapping().getCategories()));
    }

    @Test
    public void boardFindByIdTest() {
        Long firstId = initIdMap.get("firstId");
        Board board = boardRepository.findById(firstId).get();
        assertThat(board.getId()).isEqualTo(firstId);
    }

    @Test
    public void boardCategoryFindTest() {
        Long firstId = initIdMap.get("firstId");
        Board board = boardRepository.findById(firstId).get();
        BoardCategoryMapping boardCategoryMapping = board.getBoardCategoryMapping();
        List<BoardCategory> categories = boardCategoryMapping.getCategories();
        assertThat(categories).size().isEqualTo(2);
    }
    @Test
    public void boardCategoryUpdateTest() {
        Long firstId = initIdMap.get("firstId");
        Board board = boardRepository.findById(firstId).get();
        BoardCategoryMapping boardCategoryMapping = board.getBoardCategoryMapping();
        List<BoardCategory> categories = boardCategoryMapping.getCategories();
        BoardCategory boardCategory = categories.get(0);
        UUID boardCategoryId = boardCategory.getId();
        boardCategoryMapping.updateBoardCategoryMapping(boardCategoryId, "updateUpdate");
        assertThat(boardCategory.getCategoryName()).isEqualTo("updateUpdate");
    }

    @Test
    public void boardPageableTest() {
        PageRequest page = PageRequest.of(0, 10, Sort.by("createDate").descending());
        Page<Board> all = boardRepository.findAll(page);
        System.out.println("all = " + all.getContent());
        System.out.println("all.getPageable() = " + all.getPageable());
        System.out.println("all.getNumber() = " + all.getNumber());
        System.out.println("all.getSize() = " + all.getSize());
        System.out.println("all.getTotalElements() = " + all.getTotalElements());
        System.out.println("all.getTotalPages() = " + all.getTotalPages());
        assertThat(all).size().isEqualTo(10);
    }
}