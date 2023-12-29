package sideproject.fileservice.file.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sideproject.fileservice.file.dto.item.FindFileItem;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FindFileResponse {
    private FindFileItem items;
}
