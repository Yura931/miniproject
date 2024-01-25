package sideproject.boardservice.messagequeue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import sideproject.boardservice.post.dto.kafka.Field;
import sideproject.boardservice.post.dto.kafka.KafkaRegisterPostDto;
import sideproject.boardservice.post.dto.kafka.Payload;
import sideproject.boardservice.post.dto.kafka.Schema;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class PostProducer {

    private KafkaTemplate<String, String> kafkaTemplate;
    private ObjectMapper objectMapper;
    List<Field> fields = Arrays.asList(
            Field.createField("string", true, "post_id"),
            Field.createField("int64", true, "board_id"),
            Field.createField("string", true, "board_category_id"),
            Field.createField("string", true, "post_title"),
            Field.createField("string", true, "post_content"),
            Field.createField("int64", true, "view_count"),
            Field.createField("string", true, "file_mapping_id")
    );

    Schema schema = Schema.createSchema("struct", fields, false, "post");

    @Autowired
    public PostProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public KafkaRegisterPostDto send(String topic, KafkaRegisterPostDto dto) {
        Payload payload = Payload.createPayload(dto);
        String jsonInString = null;
        try {
            jsonInString = objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        kafkaTemplate.send(topic, jsonInString);
        log.info("Kafka Producer sent data from the Post microservice: {}", dto);
        return dto;
    }
}
