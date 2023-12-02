package subproject.admin.board.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import subproject.admin.board.entity.BoardCategory;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardMasterRequest {

    private UUID id;

    private String boardEnabled;

    private String boardVisible;

    private String boardType;

    private String boardMasterTitle;

    private String boardMasterDescription;

    private String boardCategoryEnabled;

    private String boardFileEnabled;

    private String boardCommentEnabled;

    private String boardReplyCommentEnabled;

    private String boardWritePermission;

    private String boardReadPermission;

}
