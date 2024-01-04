package sideproject.boardservice.post.dto.item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sideproject.boardservice.post.entity.Post;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdatePostItem {
    private UUID postId;
    private Long boardId;
    private String categoryName;
    private String postTitle;
    private String postContent;
    private Long viewCount;

    public UpdatePostItem(UUID postId, Long boardId, String categoryName, String postTitle, String postContent, Long viewCount) {
        this.postId = postId;
        this.boardId = boardId;
        this.categoryName = categoryName;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.viewCount = viewCount;
    }

    public static UpdatePostItem postEntityToDto(Post post) {
        return new UpdatePostItem(
                post.getId(),
                post.getBoard().getId(),
                post.getBoardCategory().getCategoryName(),
                post.getPostTitle(),
                post.getPostContent(),
                post.getViewCount()
        );
    }

}
