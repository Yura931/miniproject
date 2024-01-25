package sideproject.boardservice.post.dto.kafka;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Field {
    private String type;
    private boolean optional;
    private String field;

    @Builder(access = AccessLevel.PRIVATE)
    private Field(String type, boolean optional, String field) {
        this.type = type;
        this.optional = optional;
        this.field = field;
    }

    public static Field createField(String type, boolean optional, String field) {
        return Field.builder()
                .type(type)
                .optional(optional)
                .field(field)
                .build();
    }
}
