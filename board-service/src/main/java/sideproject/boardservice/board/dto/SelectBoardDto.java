package sideproject.boardservice.board.dto;

public record SelectBoardDto(
        Long id
) {
    public static SelectBoardDto from (Long id) {
        return new SelectBoardDto(id);
    }
}
