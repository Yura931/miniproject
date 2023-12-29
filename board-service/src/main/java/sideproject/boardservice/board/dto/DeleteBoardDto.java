package sideproject.boardservice.board.dto;

public record DeleteBoardDto(
        Long id
) {

    public static DeleteBoardDto of (Long id) {
        return new DeleteBoardDto(id);
    }
}
