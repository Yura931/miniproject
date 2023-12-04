package subproject.admin.board.entity;

import jakarta.persistence.*;
import lombok.*;
import subproject.admin.board.dto.CategoryDto;
import subproject.admin.common.entity.BaseTimeEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = { "id" })
public class BoardCategory extends BaseTimeEntity {

    @Id
    @Column(name = "board_category_id")
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", unique = true)
    private Board board;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "boardCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> categories = new ArrayList<>();

    private BoardCategory(UUID id, List<CategoryDto> categories) {
        this.id = id;
        this.categories = categories.stream()
        .map(dto -> Category.createCategory(this, dto))
        .collect(Collectors.toList());
    }

    public static BoardCategory createBoardCategory(List<CategoryDto> categories) {
        UUID uuid = UUID.randomUUID();
        return new BoardCategory(uuid, categories);
    }

}
