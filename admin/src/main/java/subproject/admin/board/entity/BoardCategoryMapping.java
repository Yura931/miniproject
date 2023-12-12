package subproject.admin.board.entity;

import jakarta.persistence.*;
import lombok.*;
import subproject.admin.board.dto.record.BoardCategoryDto;
import subproject.admin.common.entity.BaseTimeEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = { "id" })
public class BoardCategoryMapping extends BaseTimeEntity {

    @Id
    @Column(name = "board_category_mapping_id")
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", unique = true)
    private Board board;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "boardCategoryMapping", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BoardCategory> categories = new ArrayList<>();

    private BoardCategoryMapping(UUID id, List<BoardCategoryDto> categories, Board board) {
        this.id = id;
        this.categories = categories.stream()
        .map(dto -> BoardCategory.createCategory(this, dto))
        .toList();
        this.board = board;
    }

    public BoardCategoryMapping updateBoardCategoryMapping(UUID id, String updateCategoryName) {
        this.getCategories().stream()
                .filter((categories) -> categories.getId().equals(id))
                .map((category) -> category.updateCategory(updateCategoryName))
                .toList();
        return this;
    }

    public static BoardCategoryMapping createBoardCategory(List<BoardCategoryDto> categories, Board board) {
        UUID uuid = UUID.randomUUID();
        return new BoardCategoryMapping(uuid, categories, board);
    }


}
