package sideproject.fileservice.file.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import sideproject.fileservice.file.entity.enums.FileOwnerTypes;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegisterFileRequest {
    private UUID fileOwnerId;
    private FileOwnerTypes fileOwnerTypes;
}
