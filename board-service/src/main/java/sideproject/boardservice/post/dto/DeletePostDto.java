package sideproject.boardservice.post.dto;

import java.util.UUID;

public record DeletePostDto(
        UUID postId
) {

    public static DeletePostDto from (UUID postId) {
        return new DeletePostDto(postId);
    }
}
