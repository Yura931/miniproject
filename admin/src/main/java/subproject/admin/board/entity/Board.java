package subproject.admin.board.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import subproject.admin.board.dto.record.BoardCategoryDto;
import subproject.admin.board.dto.record.RegisterBoardDto;
import subproject.admin.board.entity.enums.BoardType;
import subproject.admin.board.entity.enums.Enabled;
import subproject.admin.common.entity.BaseEntity;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = { "id", "boardEnabled", "boardVisible",
                "boardType", "boardTitle", "boardDescription", "boardCategoryEnabled",
                "boardFileEnabled", "boardCommentEnabled", "boardReplyCommentEnabled"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Board extends BaseEntity {

    @Id
    @Column(name = "board_id")
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @Enumerated(EnumType.STRING)
    private Enabled boardEnabled;

    @Enumerated(EnumType.STRING)
    private Enabled boardVisible;

    private BoardType boardType;

    private String boardTitle;

    private String boardDescription;

    @Enumerated(EnumType.STRING)
    private Enabled boardCategoryEnabled;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private BoardCategoryMapping boardCategoryMapping;

    @Enumerated(EnumType.STRING)
    private Enabled boardFileEnabled;

    @Enumerated(EnumType.STRING)
    private Enabled boardCommentEnabled;

    @Enumerated(EnumType.STRING)
    private Enabled boardReplyCommentEnabled;

    private Board(Enabled boardEnabled, Enabled boardVisible, BoardType boardType, String boardTitle,
                  String boardDescription, Enabled boardCategoryEnabled, Enabled boardFileEnabled, Enabled boardCommentEnabled,
                  Enabled boardReplyCommentEnabled, List<BoardCategoryDto> categories) {
        this.boardEnabled = boardEnabled;
        this.boardVisible = boardVisible;
        this.boardType = boardType;
        this.boardTitle = boardTitle;
        this.boardDescription = boardDescription;
        this.boardCategoryEnabled = boardCategoryEnabled;
        this.boardFileEnabled = boardFileEnabled;
        this.boardCommentEnabled = boardCommentEnabled;
        this.boardReplyCommentEnabled = boardReplyCommentEnabled;
        this.boardCategoryMapping = BoardCategoryMapping.createBoardCategory(categories, this);
    }

    public static Board createBoard(RegisterBoardDto bm) {
        return new Board(
                bm.boardEnabled(),
                bm.boardVisible(),
                bm.boardType(),
                bm.boardTitle(),
                bm.boardDescription(),
                bm.boardCategoryEnabled(),
                bm.boardFileEnabled(),
                bm.boardCommentEnabled(),
                bm.boardReplyCommentEnabled(),
                bm.categories()
        );
    }

    public Board updateBoard(Enabled boardEnabled, Enabled boardVisible, BoardType boardType,
                                  String boardTitle, String boardDescription, Enabled boardCategoryEnabled,
                                  Enabled boardFileEnabled, Enabled boardCommentEnabled, Enabled boardReplyCommentEnabled) {
        this.boardEnabled = boardEnabled;
        this.boardVisible = boardVisible;
        this.boardType = boardType;
        this.boardTitle = boardTitle;
        this.boardDescription = boardDescription;
        this.boardCategoryEnabled = boardCategoryEnabled;
        this.boardFileEnabled = boardFileEnabled;
        this.boardCommentEnabled = boardCommentEnabled;
        this.boardReplyCommentEnabled = boardReplyCommentEnabled;
        return this;
    }

}
