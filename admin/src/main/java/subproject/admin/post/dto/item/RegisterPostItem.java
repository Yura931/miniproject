package subproject.admin.post.dto.item;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import subproject.admin.board.entity.Board;
import subproject.admin.board.entity.BoardCategory;
import subproject.admin.file.entity.File;
import subproject.admin.post.entity.Post;

import java.util.List;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class RegisterPostItem {
    private final UUID id;
    private final Board board;
    private final BoardCategory boardCategory;
    private final String postTitle;
    private final String postContent;

    public static RegisterPostItem PostEntityToDto(Post post) {
        return new RegisterPostItem(
                post.getId(),
                post.getBoard(),
                post.getBoardCategory(),
                post.getPostTitle(),
                post.getPostContent()
        );
    }
}
