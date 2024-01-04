package sideproject.boardservice.post.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePostRequest {
    private String postTitle;
    private String postContent;
    private String categoryName;
}
