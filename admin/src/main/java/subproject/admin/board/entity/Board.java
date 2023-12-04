package subproject.admin.board.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import subproject.admin.board.dto.CategoryDto;
import subproject.admin.board.dto.RegisterBoardDto;
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
    @Column(name = "board_id", columnDefinition = "BINARY(16)")
    @EqualsAndHashCode.Include
    private UUID id;

    @Enumerated(EnumType.STRING)
    private Enabled boardEnabled;

    @Enumerated(EnumType.STRING)
    private Enabled boardVisible;

    private String boardType;

    private String boardTitle;

    private String boardDescription;

    @Enumerated(EnumType.STRING)
    private Enabled boardCategoryEnabled;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private BoardCategory boardCategory;

    @Enumerated(EnumType.STRING)
    private Enabled boardFileEnabled;

    @Enumerated(EnumType.STRING)
    private Enabled boardCommentEnabled;

    @Enumerated(EnumType.STRING)
    private Enabled boardReplyCommentEnabled;

    private Board(UUID id, Enabled boardEnabled, Enabled boardVisible, String boardType, String boardTitle,
                  String boardDescription, Enabled boardCategoryEnabled, Enabled boardFileEnabled, Enabled boardCommentEnabled,
                  Enabled boardReplyCommentEnabled, List<CategoryDto> categories) {
        this.id = id;
        this.boardEnabled = boardEnabled;
        this.boardVisible = boardVisible;
        this.boardType = boardType;
        this.boardTitle = boardTitle;
        this.boardDescription = boardDescription;
        this.boardCategoryEnabled = boardCategoryEnabled;
        this.boardFileEnabled = boardFileEnabled;
        this.boardCommentEnabled = boardCommentEnabled;
        this.boardReplyCommentEnabled = boardReplyCommentEnabled;
        this.boardCategory = BoardCategory.createBoardCategory(categories);
    }

    public static Board createBoard(RegisterBoardDto bm) {
        UUID id = UUID.randomUUID();

        return new Board(
                id,
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

    public void updateBoard(Enabled boardEnabled, Enabled boardVisible, String boardType,
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
    }

}
