package subproject.admin.board.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import subproject.admin.board.dto.BoardMasterRequest;
import subproject.admin.common.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = { "id", "boardEnabled", "boardVisible",
                "boardType", "boardMasterTitle", "boardMasterDescription", "boardCategoryEnabled",
                "boardFileEnabled", "boardCommentEnabled", "boardReplyCommentEnabled", "boardWritePermission", "boardReadPermission"})
public class BoardMaster extends BaseEntity {

    @Id
    @Column(name = "board_master_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Enumerated(EnumType.STRING)
    private Enabled boardEnabled;

    @Enumerated(EnumType.STRING)
    private Enabled boardVisible;

    private String boardType;

    private String boardMasterTitle;

    private String boardMasterDescription;

    @Enumerated(EnumType.STRING)
    private Enabled boardCategoryEnabled;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "boardMaster")
    @JsonIgnore
    private List<BoardCategory> boardCategory = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Enabled boardFileEnabled;

    @Enumerated(EnumType.STRING)
    private Enabled boardCommentEnabled;

    @Enumerated(EnumType.STRING)
    private Enabled boardReplyCommentEnabled;

    private String boardWritePermission;

    private String boardReadPermission;

    @Builder(access = AccessLevel.PRIVATE)
    private BoardMaster(UUID id, Enabled boardEnabled, Enabled boardVisible, String boardType, String boardMasterTitle, String boardMasterDescription, Enabled boardCategoryEnabled, Enabled boardFileEnabled, Enabled boardCommentEnabled, Enabled boardReplyCommentEnabled, String boardWritePermission, String boardReadPermission) {
        this.id = id;
        this.boardEnabled = boardEnabled;
        this.boardVisible = boardVisible;
        this.boardType = boardType;
        this.boardMasterTitle = boardMasterTitle;
        this.boardMasterDescription = boardMasterDescription;
        this.boardCategoryEnabled = boardCategoryEnabled;
        this.boardFileEnabled = boardFileEnabled;
        this.boardCommentEnabled = boardCommentEnabled;
        this.boardReplyCommentEnabled = boardReplyCommentEnabled;
        this.boardWritePermission = boardWritePermission;
        this.boardReadPermission = boardReadPermission;
    }

    public static BoardMaster createBoardMaster(BoardMasterRequest boardMasterRequest) {
        UUID uuid = UUID.randomUUID();
        return new BoardMaster().builder()
                .id(uuid)
                .boardEnabled(Enabled.valueOf(boardMasterRequest.getBoardEnabled()))
                .boardVisible(Enabled.valueOf(boardMasterRequest.getBoardVisible()))
                .boardType(boardMasterRequest.getBoardType())
                .boardMasterTitle(boardMasterRequest.getBoardMasterTitle())
                .boardMasterDescription(boardMasterRequest.getBoardMasterDescription())
                .boardCategoryEnabled(Enabled.valueOf(boardMasterRequest.getBoardCategoryEnabled()))
                .boardFileEnabled(Enabled.valueOf(boardMasterRequest.getBoardFileEnabled()))
                .boardCommentEnabled(Enabled.valueOf(boardMasterRequest.getBoardCommentEnabled()))
                .boardReplyCommentEnabled(Enabled.valueOf(boardMasterRequest.getBoardReplyCommentEnabled()))
                .boardWritePermission(boardMasterRequest.getBoardWritePermission())
                .boardReadPermission(boardMasterRequest.getBoardReadPermission())
                .build();
    }

    public void saveBoardCategory(List<BoardCategory> boardCategory) {
        this.boardCategory = boardCategory;
    }

    private void UpdateBoardMaster() {
        this.boardEnabled = boardEnabled;
        this.boardVisible = boardVisible;
        this.boardType = boardType;
        this.boardMasterTitle = boardMasterTitle;
        this.boardMasterDescription = boardMasterDescription;
        this.boardCategoryEnabled = boardCategoryEnabled;
        this.boardFileEnabled = boardFileEnabled;
        this.boardCommentEnabled = boardCommentEnabled;
        this.boardReplyCommentEnabled = boardReplyCommentEnabled;
        this.boardWritePermission = boardWritePermission;
        this.boardReadPermission = boardReadPermission;
    }
}
