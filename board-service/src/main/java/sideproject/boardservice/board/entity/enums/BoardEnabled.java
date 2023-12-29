package sideproject.boardservice.board.entity.enums;

import lombok.Getter;
import sideproject.boardservice.common.enums.EnumDto;
import sideproject.boardservice.common.enums.EnumModel;

import java.util.List;
import java.util.stream.Stream;

@Getter
public enum BoardEnabled implements EnumModel {
    Y("사용"), N("사용 안함");
    private final String value;

    BoardEnabled(String value) {
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

    public static List<EnumDto> getEnabledList() {
        return Stream.of(BoardEnabled.values())
                .map(EnumDto::new)
                .toList();
    }
}
