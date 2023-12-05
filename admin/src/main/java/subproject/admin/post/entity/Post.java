package subproject.admin.post.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import subproject.admin.board.entity.Board;
import subproject.admin.board.entity.Category;
import subproject.admin.common.entity.BaseEntity;
import subproject.admin.post.dto.RegisterPostDto;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = { "id", "postTitle", "postContent"})
public class Post extends BaseEntity {

    @Id
    @Column(name = "post_id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", unique = false)
    private Category category;

    private String postTitle;

    private String postContent;

    private Post(UUID id, Board board, Category category, String postTitle, String postContent) {
        this.id = id;
        this.board = board;
        this.category = category;
        this.postTitle = postTitle;
        this.postContent = postContent;
    }

    public static Post createPost(RegisterPostDto dto) {
        UUID id = UUID.randomUUID();
        return new Post(
                id,
                dto.board(),
                dto.category(),
                dto.postTitle(),
                dto.postContent()
        );
    }


}
