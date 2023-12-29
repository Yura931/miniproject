package sideproject.boardservice.board.entity;

import jakarta.persistence.*;
import lombok.*;
import sideproject.boardservice.board.dto.BoardCategoryDto;
import sideproject.boardservice.board.dto.RegisterBoardDto;
import sideproject.boardservice.board.entity.enums.BoardEnabled;
import sideproject.boardservice.board.entity.enums.BoardType;
import sideproject.boardservice.common.entity.BaseEntity;
import sideproject.boardservice.post.entity.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = { "id", "boardEnabled", "boardVisible",
        "boardType", "boardTitle", "boardDescription", "boardCategoryEnabled",
        "boardFileEnabled", "boardCommentEnabled", "boardReplyCommentEnabled"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Board extends BaseEntity {

    @Id
    @Column(name = "board_id")
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @Enumerated(EnumType.STRING)
    private BoardEnabled boardEnabled;

    @Enumerated(EnumType.STRING)
    private BoardEnabled boardVisible;

    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    private String boardTitle;

    private String boardDescription;

    @Enumerated(EnumType.STRING)
    private BoardEnabled boardCategoryEnabled;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "board", cascade = CascadeType.ALL)
    private List<BoardCategory> categories = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private BoardEnabled boardFileEnabled;

    @Enumerated(EnumType.STRING)
    private BoardEnabled boardCommentEnabled;

    @Enumerated(EnumType.STRING)
    private BoardEnabled boardReplyCommentEnabled;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "board")
    private List<Post> posts = new ArrayList<>();

    private Board(BoardEnabled boardEnabled, BoardEnabled boardVisible, BoardType boardType, String boardTitle,
                  String boardDescription, BoardEnabled boardCategoryEnabled, BoardEnabled boardFileEnabled, BoardEnabled boardCommentEnabled,
                  BoardEnabled boardReplyCommentEnabled, List<BoardCategoryDto> categories) {
        this.boardEnabled = boardEnabled;
        this.boardVisible = boardVisible;
        this.boardType = boardType;
        this.boardTitle = boardTitle;
        this.boardDescription = boardDescription;
        this.boardCategoryEnabled = boardCategoryEnabled;
        this.boardFileEnabled = boardFileEnabled;
        this.boardCommentEnabled = boardCommentEnabled;
        this.boardReplyCommentEnabled = boardReplyCommentEnabled;
        this.categories = BoardCategory.createCategories(this, categories);
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

    public Board updateBoard(BoardEnabled boardEnabled, BoardEnabled boardVisible, BoardType boardType,
                             String boardTitle, String boardDescription, BoardEnabled boardCategoryEnabled,
                             BoardEnabled boardFileEnabled, BoardEnabled boardCommentEnabled, BoardEnabled boardReplyCommentEnabled) {
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

    public Board addBoardCategory(BoardCategoryDto dto) {
        this.categories.add(BoardCategory.createCategory(this, dto));
        return this;
    }

    public Board deleteBoardCategory(UUID categoryId) {
        this.categories.removeIf(category -> category.getId().equals(categoryId));
        return this;
    }

}
