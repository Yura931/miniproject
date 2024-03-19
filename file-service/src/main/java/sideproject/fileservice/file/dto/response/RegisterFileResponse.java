package sideproject.fileservice.file.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sideproject.fileservice.file.dto.item.RegisterFileItem;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class RegisterFileResponse {
    private List<RegisterFileItem> items;
}
