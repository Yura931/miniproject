package subproject.admin.board.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import subproject.admin.board.dto.command.BoardMasterCommand;
import subproject.admin.board.dto.request.BoardMasterRequest;

import java.util.Arrays;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Rollback(value = false)
@Transactional
class BoardMasterTest {

    @PersistenceContext
    EntityManager em;

    @Test
    public void boardMasterInsert() throws Exception {
        BoardMasterCommand boardMasterCommand = BoardMasterCommand.of(
                Enabled.Y,
                Enabled.Y,
                "A",
                "title",
                "description",
                Enabled.Y,
                Enabled.Y,
                Enabled.Y,
                Enabled.Y
        );
        BoardMaster boardMaster = BoardMaster.createBoardMaster(boardMasterCommand);


        BoardCategory category1 = BoardCategory.createBoardCategory("category1");
        BoardCategory category2 = BoardCategory.createBoardCategory("category2");
        boardMaster.saveBoardCategory(Arrays.asList(category1, category2));

        em.persist(boardMaster);
        BoardMaster findBoardMaster = em.find(BoardMaster.class, boardMaster.getId());
        findBoardMaster.updateBoardMaster(
                Enabled.Y,
                Enabled.Y,
                "A",
                "title2",
                "description2",
                Enabled.Y,
                Enabled.Y,
                Enabled.Y,
                Enabled.Y);
        assertThat(boardMaster.getId()).isEqualTo(findBoardMaster.getId());
        assertThat(boardMaster.getBoardCategory().size()).isEqualTo(2);

        System.out.println("findBoardMaster = " + findBoardMaster);
    }

}