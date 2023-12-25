package subproject.admin.post.dto.record;

import java.util.UUID;

public record SelectPostDto(
        UUID id
) {
    public static SelectPostDto of(UUID postId) {
        return new SelectPostDto(postId);
    }
}
