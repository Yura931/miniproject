package sideproject.boardservice.board.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import sideproject.boardservice.board.dto.BoardCategoryDto;
import sideproject.boardservice.post.entity.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = { "id", "categoryName" })
public class BoardCategory {

    @Id
    @Column(name = "board_category_id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    @JsonIgnore
    private Board board;

    private String categoryName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "boardCategory")
    @JsonIgnore
    private List<Post> post = new ArrayList<>();

    private BoardCategory(UUID id, Board board, String categoryName) {
        this.id = id;
        this.board = board;
        this.categoryName = categoryName;
    }

    public static List<BoardCategory> createCategories(Board board, List<BoardCategoryDto> categories) {
        return categories.stream()
                .map((dto) -> new BoardCategory(
                            UUID.randomUUID(),
                            board,
                            dto.categoryName()
                    )).toList();
    }

    public static BoardCategory createCategory(Board board, BoardCategoryDto dto) {
        return new BoardCategory(
                UUID.randomUUID(),
                board,
                dto.categoryName()
        );
    }

    public BoardCategory updateCategory(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

}
