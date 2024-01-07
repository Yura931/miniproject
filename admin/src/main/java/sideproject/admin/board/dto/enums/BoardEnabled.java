package sideproject.admin.board.dto.enums;

import lombok.Getter;
import sideproject.admin.common.enums.EnumDto;
import sideproject.admin.common.enums.EnumModel;

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

    public String getValue() {
        return this.value;
    }

    public static List<EnumDto> getEnabledList() {
        return Stream.of(BoardEnabled.values())
                .map(EnumDto::new)
                .toList();
    }
}
