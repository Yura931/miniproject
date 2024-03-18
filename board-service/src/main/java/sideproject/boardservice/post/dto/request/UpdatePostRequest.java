package sideproject.boardservice.post.dto.request;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdatePostRequest {
    private String postTitle;
    private String postContent;
    private String categoryName;
    private UUID createBy;
}
