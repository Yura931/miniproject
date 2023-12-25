package subproject.admin.board.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import subproject.admin.board.dto.record.RegisterBoardDto;
import subproject.admin.board.dto.request.RegisterBoardRequest;
import subproject.admin.board.entity.enums.BoardType;
import subproject.admin.board.entity.enums.Enabled;
import subproject.admin.common.enums.EnumDto;

import javax.sound.sampled.EnumControl;
import java.beans.IntrospectionException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Rollback(value = false)
@Transactional
class BoardTest {

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
        em.persist(board);
    }

    @Test
    public void boardInsert() throws Exception {
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
        em.persist(board);

        Board findBoard = em.find(Board.class, board.getId());

        assertThat(board.getId()).isEqualTo(findBoard.getId());

    }

    @Test
    public void boardUpdateTest() {
        List<Board> bm = em.createQuery("select bm from Board bm", Board.class)
                .getResultList();

        Long findId = bm.get(0).getId();
        Board findBoard = em.find(Board.class, findId);
        findBoard.updateBoard(
                Enabled.Y,
                Enabled.Y,
                BoardType.GENERAL,
                "title2",
                "description2",
                Enabled.Y,
                Enabled.Y,
                Enabled.Y, Enabled.Y);
        assertThat(findBoard.getBoardTitle()).isEqualTo("title2");
    }

    @Test
    public void enumsTest() {
        Field[] declaredFields = Enabled.class.getDeclaredFields();
        System.out.println("declaredFields = " + declaredFields);
    }

    @Test
    public void fieldNameTest() throws IntrospectionException {
        List<Map<String, List<EnumDto>>> filedName = Board.getEnabled();
        filedName.forEach(System.out::println);
    }
}