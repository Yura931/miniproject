package sideproject.boardservice.post.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import sideproject.boardservice.board.entity.Board;
import sideproject.boardservice.board.entity.BoardCategory;
import sideproject.boardservice.comment.entity.Comment;
import sideproject.boardservice.common.entity.BaseEntity;
import sideproject.boardservice.post.dto.RegisterPostDto;
import sideproject.boardservice.post.dto.UpdatePostDto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = { "id", "postTitle", "postContent", "viewCount"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Post extends BaseEntity {

    @Id
    @Column(name = "post_id")
    @EqualsAndHashCode.Include
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    @JsonIgnore
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_category_id")
    @JsonIgnore
    private BoardCategory boardCategory;

    @Column(length = 150)
    private String postTitle;

    @Column(columnDefinition = "LONGTEXT")
    private String postContent;

    @ColumnDefault("0")
    private Long viewCount = 0L;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();


    private Post(UUID id, Board board, String postTitle, String postContent) {
        this.id = id;
        this.board = board;
        this.postTitle = postTitle;
        this.postContent = postContent;
    }

    public static Post createPost(RegisterPostDto dto, Board board) {
        return new Post(
                dto.postId(),
                board,
                dto.postTitle(),
                dto.postContent()
        );
    }

    public void addBoardCategory(BoardCategory boardCategory) {
        this.boardCategory = boardCategory;
    }

    public void updatePost(UpdatePostDto dto) {
        this.postTitle = dto.postTitle();
        this.postContent = dto.postContent();
        this.boardCategory.updateCategory(dto.categoryName());
    }

    public void updateViewCount() {
        this.viewCount = this.viewCount + 1;
    }


}
