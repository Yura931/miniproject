package subproject.admin.board.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import subproject.admin.board.dto.BoardMasterRequest;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Rollback(value = false)
@Transactional
class BoardMasterTest {

    @PersistenceContext
    EntityManager em;

    @Test
    public void boardMasterInsert() throws Exception {
        BoardMasterRequest boardMasterRequest = new BoardMasterRequest();
        boardMasterRequest.setId(UUID.randomUUID());
        boardMasterRequest.setBoardEnabled(Enabled.Y.name());
        boardMasterRequest.setBoardVisible(Enabled.Y.name());
        boardMasterRequest.setBoardType("A");
        boardMasterRequest.setBoardMasterTitle("title");
        boardMasterRequest.setBoardMasterDescription("description");
        boardMasterRequest.setBoardCategoryEnabled(Enabled.Y.name());
        boardMasterRequest.setBoardFileEnabled(Enabled.Y.name());
        boardMasterRequest.setBoardCommentEnabled(Enabled.Y.name());
        boardMasterRequest.setBoardCommentEnabled(Enabled.Y.name());
        boardMasterRequest.setBoardReplyCommentEnabled(Enabled.Y.name());
        boardMasterRequest.setBoardWritePermission("A");
        boardMasterRequest.setBoardReadPermission("A");

        BoardMaster boardMaster = BoardMaster.createBoardMaster(boardMasterRequest);


        BoardCategory category1 = BoardCategory.createBoardCategory("category1");
        BoardCategory category2 = BoardCategory.createBoardCategory("category2");
        boardMaster.saveBoardCategory(Arrays.asList(category1, category2));

        em.persist(boardMaster);
        BoardMaster findBoardMaster = em.find(BoardMaster.class, boardMaster.getId());

        assertThat(boardMaster.getId()).isEqualTo(findBoardMaster.getId());
        assertThat(boardMaster.getBoardCategory().size()).isEqualTo(2);

        System.out.println("findBoardMaster = " + findBoardMaster);
    }

}