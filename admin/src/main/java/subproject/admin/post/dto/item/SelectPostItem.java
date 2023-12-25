package subproject.admin.post.dto.item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import subproject.admin.board.entity.Board;
import subproject.admin.board.entity.BoardCategory;
import subproject.admin.file.entity.File;
import subproject.admin.post.entity.Post;

import java.util.List;
import java.util.UUID;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SelectPostItem {

    private final UUID id;
    private final Board board;
    private final BoardCategory boardCategory;
    private final String postTitle;
    private final String postContent;
    private final List<File> files;

    public static SelectPostItem PostEntityToDto(Post post, List<File> files) {
        return new SelectPostItem(
                post.getId(),
                post.getBoard(),
                post.getBoardCategory(),
                post.getPostTitle(),
                post.getPostContent(),
                files
        );
    }
}
