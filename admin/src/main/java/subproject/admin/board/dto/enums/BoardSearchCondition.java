package subproject.admin.board.dto.enums;

import subproject.admin.common.enums.EnumDto;
import subproject.admin.common.enums.EnumModel;

import java.util.List;
import java.util.stream.Stream;

public enum BoardSearchCondition implements EnumModel {
    ALL("전체"),
    TITLE("제목"),
    DESCRIPTION("설명");

    private final String value;
    BoardSearchCondition(String value) {
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

    public static List<EnumDto> getBoardSearchCondition() {
        return Stream.of(BoardSearchCondition.values())
                .map(EnumDto::new)
                .toList();
    }
}
