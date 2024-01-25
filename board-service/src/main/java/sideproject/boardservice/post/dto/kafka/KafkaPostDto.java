package sideproject.boardservice.post.dto.kafka;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class KafkaPostDto implements Serializable {
    private Schema schema;
    private Payload payload;

    @Builder
    public KafkaPostDto(Schema schema, Payload payload) {
        this.schema = schema;
        this.payload = payload;
    }

}
