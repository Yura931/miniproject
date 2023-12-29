package sideproject.boardservice.common.enums;

import lombok.Getter;

@Getter
public class EnumDto {

    private String key;
    private String value;

    public EnumDto(EnumModel enumModel) {
        this.key = enumModel.getKey();
        this.value = enumModel.gatValue();
    }
}
