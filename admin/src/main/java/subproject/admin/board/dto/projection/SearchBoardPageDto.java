package subproject.admin.board.dto.projection;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import subproject.admin.board.entity.enums.Enabled;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@ToString(of = { "id", "boardEnabled", "boardVisible", "boardType", "boardTitle", "createDate" })
@Getter
public class SearchBoardPageDto {
    private UUID id;
    private Enabled boardEnabled;
    private Enabled boardVisible;
    private String boardType;
    private String boardTitle;
    private LocalDateTime createDate;
}
