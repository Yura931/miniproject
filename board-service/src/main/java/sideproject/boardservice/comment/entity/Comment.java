package sideproject.boardservice.comment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

    public static Comment createComment() {
        return new Comment();
    }

}
