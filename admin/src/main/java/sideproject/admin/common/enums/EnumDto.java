package sideproject.admin.common.enums;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(of = { "key", "value" })
public class EnumDto {
    private String key;
    private String value;

    public EnumDto(EnumModel enumModel) {
        this.key = enumModel.getKey();
        this.value = enumModel.getValue();
    }
}
