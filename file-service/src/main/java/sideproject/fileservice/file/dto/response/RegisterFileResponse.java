package sideproject.fileservice.file.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sideproject.fileservice.file.dto.item.RegisterFileItem;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class RegisterFileResponse {
    private RegisterFileItem items;
}
