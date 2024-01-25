package sideproject.boardservice.post.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegisterPostRequest {
    private String postTitle;
    private String postContent;
    private UUID categoryId;
    private List<MultipartFile> files;
}
