package sideproject.boardservice.board.entity.enums;

import lombok.Getter;
import sideproject.boardservice.common.enums.EnumModel;

@Getter
public enum BoardFileEnabled implements EnumModel {
    Y("사용"), N("사용 안함");
    private final String value;

    BoardFileEnabled(String value) {
        this.value = value;
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String gatValue() {
        return this.value;
    }
}
