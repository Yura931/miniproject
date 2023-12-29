package sideproject.boardservice.post.dto.item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import sideproject.boardservice.board.entity.Board;
import sideproject.boardservice.board.entity.BoardCategory;
import sideproject.boardservice.post.entity.Post;

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

    public static SelectPostItem PostEntityToDto(Post post) {
        return new SelectPostItem(
                post.getId(),
                post.getBoard(),
                post.getBoardCategory(),
                post.getPostTitle(),
                post.getPostContent()
        );
    }
}
