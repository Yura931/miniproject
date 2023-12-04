package subproject.admin.board.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import subproject.admin.board.dto.CategoryDto;
import subproject.admin.post.entity.Post;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = { "id", "categoryName" })
public class Category {

    @Id
    @Column(name = "category_id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_category_id")
    private BoardCategory boardCategory;

    private String categoryName;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "category")
    private Post post;

    private Category(UUID id, BoardCategory boardCategory, CategoryDto dto) {
        this.id = id;
        boardCategory.getCategories().add(this);
        this.categoryName = dto.categoryName();
    }

    public static Category createCategory(BoardCategory boardCategory, CategoryDto dto) {
        UUID id = UUID.randomUUID();
        return new Category(id, boardCategory, dto);
    }

}
