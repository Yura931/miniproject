package sideproject.boardservice.post.dto.item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sideproject.boardservice.board.entity.Board;
import sideproject.boardservice.board.entity.BoardCategory;
import sideproject.boardservice.common.dto.FileDto;
import sideproject.boardservice.post.entity.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SelectPostItem {

    private UUID id;
    private Board board;
    private BoardCategory boardCategory;
    private String postTitle;
    private String postContent;
    private List<FileDto> files = new ArrayList<>();

    public SelectPostItem(UUID id, Board board, BoardCategory boardCategory, String postTitle, String postContent) {
        this.id = id;
        this.board = board;
        this.boardCategory = boardCategory;
        this.postTitle = postTitle;
        this.postContent = postContent;
    }

    public static SelectPostItem postEntityToDto(Post post) {
        return new SelectPostItem(
                post.getId(),
                post.getBoard(),
                post.getBoardCategory(),
                post.getPostTitle(),
                post.getPostContent()
        );
    }

    public void addFiles(List<FileDto> files) {
        this.files = files;
    }
}
