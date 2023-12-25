package subproject.admin.board.dto.record;

public record SelectBoardDto(
        Long id
) {
    public static SelectBoardDto from (Long id) {
        return new SelectBoardDto(id);
    }
}
