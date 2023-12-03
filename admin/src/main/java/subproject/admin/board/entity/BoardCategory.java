package subproject.admin.board.entity;

import jakarta.persistence.*;
import lombok.*;
import subproject.admin.common.entity.BaseTimeEntity;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = { "id", "boardCategoryName" })
public class BoardCategory extends BaseTimeEntity {

    @Id
    @Column(name = "board_category_id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    private String boardCategoryName;

    private BoardCategory(UUID id, String boardCategoryName) {
        this.id = id;
        this.boardCategoryName = boardCategoryName;
    }

    public static BoardCategory createBoardCategory(String boardCategoryName) {
        UUID uuid = UUID.randomUUID();
        return new BoardCategory(uuid, boardCategoryName);
    }

    public void UpdateBoardCategory (String boardCategoryName) {
        this.boardCategoryName = boardCategoryName;
    }


}
