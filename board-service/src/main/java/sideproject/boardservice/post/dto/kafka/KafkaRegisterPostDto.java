package sideproject.boardservice.post.dto.kafka;

import sideproject.boardservice.post.entity.Post;

import java.time.LocalDateTime;
import java.util.UUID;

public record KafkaRegisterPostDto (
        UUID postId,
        Long boardId,
        String postTitle,
        String postContent,
        Long viewCount,
        LocalDateTime createDate,
        LocalDateTime modifiedDate,
        UUID createdBy,
        UUID lastModifiedBy
) {
    public static KafkaRegisterPostDto postEntityToDto(Post post) {
        return new KafkaRegisterPostDto(
                post.getId(),
                post.getBoard().getId(),
                post.getPostTitle(),
                post.getPostContent(),
                post.getViewCount(),
                post.getCreateDate(),
                post.getLastModifiedDate(),
                post.getCreatedBy(),
                post.getLastModifiedBy()
        );
    }
}
