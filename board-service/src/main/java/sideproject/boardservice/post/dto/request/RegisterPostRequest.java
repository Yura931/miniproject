package sideproject.boardservice.post.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterPostRequest {

    private String postTitle;
    private String postContent;
    private UUID categoryId;
}
