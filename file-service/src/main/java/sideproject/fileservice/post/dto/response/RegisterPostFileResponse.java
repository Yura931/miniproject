package sideproject.fileservice.post.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sideproject.fileservice.post.dto.item.RegisterPostFileItem;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class RegisterPostFileResponse {
    private List<RegisterPostFileItem> items;
}
