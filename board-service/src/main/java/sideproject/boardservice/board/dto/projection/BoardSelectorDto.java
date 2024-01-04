package sideproject.boardservice.board.dto.projection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BoardSelectorDto {
    private Long id;
    private String boardTitle;
}
