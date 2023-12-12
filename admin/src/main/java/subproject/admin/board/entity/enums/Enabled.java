package subproject.admin.board.entity.enums;

import com.fasterxml.jackson.databind.util.EnumValues;
import lombok.Getter;
import lombok.ToString;
import subproject.admin.common.enums.EnumDto;
import subproject.admin.common.enums.EnumModel;

import java.util.List;
import java.util.stream.Stream;

@Getter
public enum Enabled implements EnumModel {
    Y("표시"), N("표시 안함");
    private final String value;

    Enabled(String value) {
        this.value = value;
    }

    @Override
    public String getKey() {
        return name();
    }

    public String getValue() {
        return value;
    }


    public static List<EnumDto> getEnabledList() {
        return Stream.of(Enabled.values())
                .map(EnumDto::new)
                .toList();
    }
}
