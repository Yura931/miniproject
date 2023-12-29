package sideproject.boardservice.board.dto.enums;

import sideproject.boardservice.common.enums.EnumDto;
import sideproject.boardservice.common.enums.EnumModel;

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
    public String gatValue() {
        return this.value;
    }

    public static List<EnumDto> getBoardSearchCondition() {
        return Stream.of(BoardSearchCondition.values())
                .map(EnumDto::new)
                .toList();
    }
}
