package sideproject.boardservice.board.dto.projection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import sideproject.boardservice.board.entity.enums.BoardEnabled;
import sideproject.boardservice.board.entity.enums.BoardType;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@ToString(of = { "id", "boardEnabled", "boardVisible", "boardType", "boardTitle", "createDate" })
@Getter
public class SearchBoardPageDto {
    private Long id;
    private BoardEnabled boardEnabled;
    private BoardEnabled boardVisible;
    private BoardType boardType;
    private String boardTitle;
    private LocalDateTime createDate;
    private String createdBy;
}
