package sideproject.admin.board.dto.enums;

import lombok.Getter;
import sideproject.admin.common.enums.EnumDto;
import sideproject.admin.common.enums.EnumModel;

import java.util.List;
import java.util.stream.Stream;

@Getter
public enum BoardSortCondition implements EnumModel {

    CREATE_AT("생성일"),
    CREATE_BY("작성자"),
    BOARD_TYPE("게시판 타입");

    private final String value;

    BoardSortCondition(String value) {
        this.value = value;
    }

    @Override
    public String getKey() {
        return name();
    }

    public String getValue() {
        return this.value;
    }

    public static List<EnumDto> getBoardSortCondition() {
        return Stream.of(BoardSortCondition.values())
                .map(EnumDto::new)
                .toList();
    }
}
