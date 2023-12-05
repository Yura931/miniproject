package subproject.admin.post.dto.item;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import subproject.admin.board.entity.Board;
import subproject.admin.board.entity.Category;
import subproject.admin.post.entity.Post;

import java.util.UUID;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PostItem {

    private final UUID id;
    private final Board board;
    private final Category category;
    private final String postTitle;
    private final String postContent;

    public static PostItem PostEntityToDto(Post post) {
        return new PostItem(
                post.getId(),
                post.getBoard(),
                post.getCategory(),
                post.getPostTitle(),
                post.getPostContent()
        );
    }
}
