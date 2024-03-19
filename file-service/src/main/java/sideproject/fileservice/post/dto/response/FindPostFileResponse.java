package sideproject.fileservice.post.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sideproject.fileservice.post.dto.item.FindPostFileItem;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FindPostFileResponse {
    private FindPostFileItem items;
}
