package sideproject.boardservice.post.dto.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sideproject.boardservice.post.entity.Post;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterPostItem {
    private UUID postId;
    private Long boardId;
    private String postTitle;
    private String postContent;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;

    public static RegisterPostItem postEntityToDto(Post post) {
        return new RegisterPostItem(
                post.getId(),
                post.getBoard().getId(),
                post.getPostTitle(),
                post.getPostContent(),
                post.getLastModifiedBy(),
                post.getLastModifiedDate()
        );
    }
}
