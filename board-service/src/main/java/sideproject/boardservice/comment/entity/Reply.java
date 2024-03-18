package sideproject.boardservice.comment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import sideproject.boardservice.common.entity.BaseEntity;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "content"})
public class Reply extends BaseEntity {

    @Id
    @Column(name = "reply_id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    @JsonIgnore
    private Comment comment;

    private String content;

    private Reply(UUID id, Comment comment, String content) {
        this.id = id;
        this.comment = comment;
        this.content = content;
    }
    public static Reply createReply(Comment comment, String content) {
        return new Reply(
                UUID.randomUUID(),
                comment,
                content
        );
    }
    public Reply updateReply(String content) {
        this.content = content;
        return this;
    }
}
