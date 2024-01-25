package sideproject.boardservice.post.dto.kafka;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class Schema {
    private String type;
    private List<Field> fields;
    private boolean optional;
    private String name;

    @Builder(access = AccessLevel.PRIVATE)
    private Schema(String type, List<Field> fields, boolean optional, String name) {
        this.type = type;
        this.fields = fields;
        this.optional = optional;
        this.name = name;
    }

    public static Schema createSchema(String type, List<Field> fields, boolean optional, String name) {
        return Schema.builder()
                .type(type)
                .fields(fields)
                .optional(optional)
                .name(name)
                .build();
    }
}
