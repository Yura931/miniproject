package sideproject.fileservice.post.dto.item;

import sideproject.fileservice.post.dto.PostFileDto;

import java.util.List;

public record FindPostFileItem(
        List<PostFileDto> postFileDtos
) {

}
