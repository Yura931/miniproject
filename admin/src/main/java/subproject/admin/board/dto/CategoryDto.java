package subproject.admin.board.dto;


public record CategoryDto(
        String categoryName
) {
    public static CategoryDto from(String categoryName) {
        return new CategoryDto(categoryName);
    }
}
