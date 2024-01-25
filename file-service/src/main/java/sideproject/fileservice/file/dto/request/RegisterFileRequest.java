package sideproject.fileservice.file.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.util.MultiValueMap;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegisterFileRequest {
    private MultiValueMap<String, MultiValueMap> files;
}
