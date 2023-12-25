package subproject.admin.post.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import subproject.admin.board.dto.record.BoardCategoryDto;
import subproject.admin.board.entity.Board;
import subproject.admin.board.entity.BoardCategory;
import subproject.admin.common.entity.BaseEntity;
import subproject.admin.file.dto.FileDto;
import subproject.admin.file.entity.File;
import subproject.admin.post.dto.record.RegisterPostDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = { "id", "postTitle", "postContent", "viewCount"})
public class Post extends BaseEntity {

    @Id
    @Column(name = "post_id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    @JsonIgnore
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_category_id")
    @JsonIgnore
    private BoardCategory boardCategory;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
    private List<File> files = new ArrayList<>();

    private String postTitle;

    private String postContent;

    @ColumnDefault("0")
    private Long viewCount;

    private Post(UUID id, Board board, BoardCategory category, String postTitle, String postContent, Long viewCount) {
        this.id = id;
        this.board = board;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.viewCount = viewCount;
        this.boardCategory = category;
    }

    public static Post createPost(RegisterPostDto dto, Board board, BoardCategory boardCategory) {
        UUID id = UUID.randomUUID();
        return new Post(
                id,
                board,
                boardCategory,
                dto.postTitle(),
                dto.postContent(),
                dto.viewCount()
        );
    }

    public void changeCategory(BoardCategory boardCategory) {
        this.boardCategory = boardCategory;
    }

    public Post updatePost() {
        return this;
    }

    public Post updateViewCount() {
        return this;
    }

}
