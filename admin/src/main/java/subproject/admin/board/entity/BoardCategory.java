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
    @GeneratedValue
    @Column(name = "board_category_id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_master_id")
    private BoardMaster boardMaster;

    private String boardCategoryName;

    @Builder(access = AccessLevel.PROTECTED)
    private BoardCategory(UUID id, String boardCategoryName, BoardMaster boardMaster) {
        this.id = id;
        this.boardCategoryName = boardCategoryName;
    }

    public static BoardCategory createBoardCategory(String boardCategoryName) {
        UUID uuid = UUID.randomUUID();
        return new BoardCategory().builder()
                .id(uuid)
                .boardCategoryName(boardCategoryName)
                .build();
    }

    private void UpdateBoardCategory (String boardCategoryName) {
        this.boardCategoryName = boardCategoryName;
    }

}
