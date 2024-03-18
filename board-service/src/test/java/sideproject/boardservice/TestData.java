package sideproject.boardservice;

import org.springframework.stereotype.Component;
import sideproject.boardservice.board.dto.RegisterBoardDto;
import sideproject.boardservice.board.dto.request.RegisterBoardRequest;
import sideproject.boardservice.board.entity.Board;
import sideproject.boardservice.board.entity.enums.*;
import sideproject.boardservice.comment.dto.RegisterCommentDto;
import sideproject.boardservice.comment.dto.request.RegisterCommentRequest;
import sideproject.boardservice.comment.entity.Comment;
import sideproject.boardservice.comment.entity.Reply;
import sideproject.boardservice.post.dto.RegisterPostDto;
import sideproject.boardservice.post.dto.request.RegisterPostRequest;
import sideproject.boardservice.post.entity.Post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Component
public class TestData<T> {
    private Map<String, T> firstData = new HashMap<>();
    private String key = "first";

    public List<Board> boards(int range) {
        return IntStream.range(1, range + 1)
                .mapToObj(i -> Board.createBoard(RegisterBoardDto.from(
                                    registerBoardRequest(i))
                )).toList();
    }

    private RegisterBoardRequest registerBoardRequest(int range) {
        return new RegisterBoardRequest(
                    BoardEnabled.Y,
                    BoardVisible.Y,
                    BoardType.GENERAL,
                    "title" + range,
                    "description" + range,
                    BoardCategoryEnabled.Y,
                    BoardFileEnabled.Y,
                    BoardCommentEnabled.Y,
                    List.of("category1", "category2")
        );
    }

    public List<Post> posts(int range, Board board) {
        return IntStream.range(1, range + 1)
                        .mapToObj(i ->
                                Post.createPost(
                                        RegisterPostDto.of(board.getId(), registerPostRequest(i)), board
                                )
                        ).toList();
    }

    private RegisterPostRequest registerPostRequest(int range) {
        return new RegisterPostRequest().builder()
                .postTitle("post title" + range)
                .postContent("post content" + range)
                .build();
    }

    public Map<String, T> getFirstData() {
        return firstData;
    }

    public List<Comment> comments(int range, Post post) {
        return IntStream.range(1, range + 1)
                .mapToObj(i -> Comment.createComment(
                        RegisterCommentDto.of(post.getId(), new RegisterCommentRequest("content" + i))
                        , post))
                .toList();
    }

    public List<Reply> replies(int range, Comment comment) {
        return IntStream.range(1, range + 1)
                .mapToObj(i -> Reply.createReply(comment, "reply" + i))
                .toList();
    }

    public void setFirstData(String key, T value) {
        firstData.put(key, value);
    }

    public String getKey() {
        return this.key;
    }
}
