package subproject.admin.board.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import subproject.admin.board.dto.record.BoardCategoryDto;
import subproject.admin.post.entity.Post;

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
    @JoinColumn(name = "board_category_mapping_id")
    private BoardCategoryMapping boardCategoryMapping;

    private String categoryName;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "boardCategory")
    private Post post;

    private BoardCategory(UUID id, BoardCategoryMapping boardCategoryMapping, BoardCategoryDto dto) {
        this.id = id;
        boardCategoryMapping.getCategories().add(this);
        this.boardCategoryMapping = boardCategoryMapping;
        this.categoryName = dto.categoryName();
    }

    public static BoardCategory createCategory(BoardCategoryMapping boardCategoryMapping, BoardCategoryDto dto) {
        UUID id = UUID.randomUUID();
        return new BoardCategory(id, boardCategoryMapping, dto);
    }

    public BoardCategory updateCategory(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }
}
