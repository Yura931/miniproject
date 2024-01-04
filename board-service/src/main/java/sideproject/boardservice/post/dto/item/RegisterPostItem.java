package sideproject.boardservice.post.dto.item;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import sideproject.boardservice.board.entity.Board;
import sideproject.boardservice.board.entity.BoardCategory;
import sideproject.boardservice.post.entity.Post;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class RegisterPostItem {
    private final UUID id;
    private final Board board;
    private final BoardCategory boardCategory;
    private final String postTitle;
    private final String postContent;

    public static RegisterPostItem postEntityToDto(Post post) {
        return new RegisterPostItem(
                post.getId(),
                post.getBoard(),
                post.getBoardCategory(),
                post.getPostTitle(),
                post.getPostContent()
        );
    }
}
