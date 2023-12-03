package subproject.admin.post.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import subproject.admin.board.entity.Board;
import subproject.admin.board.entity.BoardCategory;
import subproject.admin.common.entity.BaseEntity;

import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_category_id")
    private BoardCategory boardCategory;

    private String postTitle;

    private String postContent;

    private Post(UUID id, Board board, BoardCategory boardCategory, String postTitle, String postContent) {
        this.id = id;
        this.board = board;
        this.boardCategory = boardCategory;
        this.postTitle = postTitle;
        this.postContent = postContent;
    }

    public static Post createPost() {
        UUID id = UUID.randomUUID();
        return new Post();
    }


}
