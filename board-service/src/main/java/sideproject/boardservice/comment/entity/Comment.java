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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    @Id
    @Column(name = "comment_id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @JsonIgnore
    private Post post;

    @Column(name = "content", length = 1000)
    private String content;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reply> replies = new ArrayList<>();

    public Comment(UUID id, Post post, String content) {
        this.id = id;
        this.post = post;
        this.content = content;
    }

    public static Comment createComment(RegisterCommentDto dto, Post post) {
        return new Comment(
                UUID.randomUUID(),
                post,
                dto.content()
        );
    }

    public Comment updateComment(String content) {
        this.content = content;
        return this;
    }

    public void addReply(Reply reply) {
        this.replies.add(reply);
    }

    public void deleteReply(Reply reply) {
        this.replies.removeIf(thisReply -> thisReply.getId().equals(reply.getId()));
    }

}
