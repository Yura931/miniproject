package sideproject.boardservice.post.dto;

import java.util.UUID;

public record SelectPostDto(
        UUID id
) {
    public static SelectPostDto of(UUID postId) {
        return new SelectPostDto(postId);
    }
}
