package sideproject.boardservice.comment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sideproject.boardservice.comment.dto.RegisterCommentDto;
import sideproject.boardservice.comment.dto.UpdateCommentDto;
import sideproject.boardservice.common.entity.BaseEntity;
import sideproject.boardservice.post.entity.Post;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @JsonIgnore
    private Post post;

    private String comment;

    public Comment(UUID id, Post post, String comment) {
        this.id = id;
        this.post = post;
        this.comment = comment;
    }

    public static Comment createComment(RegisterCommentDto dto, Post post) {
        return new Comment(
                UUID.randomUUID(),
                post,
                dto.comment()
        );
    }

    public Comment updateComment(UpdateCommentDto dto) {
        this.comment = dto.comment();
        return this;
    }
}
