package sideproject.admin.board.dto.enums;


import sideproject.admin.common.enums.EnumDto;
import sideproject.admin.common.enums.EnumModel;

import java.util.List;
import java.util.stream.Stream;

public enum BoardType implements EnumModel {
    GENERAL("일반"), GALLERY("갤러리"), PRODUCT("상품");

    private final String value;

    BoardType(String value) {
        this.value = value;
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return this.value;
    }

    public static List<EnumDto> getBoardTypeList() {
        return Stream.of(BoardType.values())
                .map(EnumDto::new)
                .toList();
    }
}
