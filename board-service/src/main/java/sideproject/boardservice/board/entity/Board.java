package sideproject.boardservice.board.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import sideproject.boardservice.board.dto.BoardCategoryDto;
import sideproject.boardservice.board.dto.RegisterBoardDto;
import sideproject.boardservice.board.dto.UpdateBoardDto;
import sideproject.boardservice.board.entity.enums.*;
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
        "boardFileEnabled", "boardCommentEnabled"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Board extends BaseEntity {

    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 2)
    private BoardEnabled boardEnabled;

    @Enumerated(EnumType.STRING)
    @Column(length = 2)
    private BoardVisible boardVisible;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private BoardType boardType;

    @Column(length = 100)
    private String boardTitle;

    @Column(length = 500)
    private String boardDescription;

    @Enumerated(EnumType.STRING)
    @Column(length = 2)
    private BoardCategoryEnabled boardCategoryEnabled;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BoardCategory> categories = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(length = 2)
    private BoardFileEnabled boardFileEnabled;

    @Enumerated(EnumType.STRING)
    @Column(length = 2)
    private BoardCommentEnabled boardCommentEnabled;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "board")
    private List<Post> posts = new ArrayList<>();

    private Board(BoardEnabled boardEnabled, BoardVisible boardVisible, BoardType boardType, String boardTitle,
                  String boardDescription, BoardCategoryEnabled boardCategoryEnabled, BoardFileEnabled boardFileEnabled, BoardCommentEnabled boardCommentEnabled,
                  List<BoardCategoryDto> categories) {
        this.boardEnabled = boardEnabled;
        this.boardVisible = boardVisible;
        this.boardType = boardType;
        this.boardTitle = boardTitle;
        this.boardDescription = boardDescription;
        this.boardCategoryEnabled = boardCategoryEnabled;
        this.boardFileEnabled = boardFileEnabled;
        this.boardCommentEnabled = boardCommentEnabled;
        this.categories = BoardCategory.createCategories(this, categories);
    }

    public static Board createBoard(RegisterBoardDto dto) {
        return new Board(
                dto.boardEnabled(),
                dto.boardVisible(),
                dto.boardType(),
                dto.boardTitle(),
                dto.boardDescription(),
                dto.boardCategoryEnabled(),
                dto.boardFileEnabled(),
                dto.boardCommentEnabled(),
                dto.categories()
        );
    }

    public Board updateBoard(UpdateBoardDto dto) {
        this.boardEnabled = dto.boardEnabled();
        this.boardVisible = dto.boardVisible();
        this.boardType = dto.boardType();
        this.boardTitle = dto.boardTitle();
        this.boardDescription = dto.boardDescription();
        this.boardCategoryEnabled = dto.boardCategoryEnabled();
        this.boardFileEnabled = dto.boardFileEnabled();
        this.boardCommentEnabled = dto.boardCommentEnabled();
        return this;
    }

    public Board addBoardCategory(BoardCategory boardCategory) {
        this.categories.add(boardCategory);
        return this;
    }

    public Board deleteBoardCategory(UUID categoryId) {
        this.categories.removeIf(category -> category.getId().equals(categoryId));
        return this;
    }

}
