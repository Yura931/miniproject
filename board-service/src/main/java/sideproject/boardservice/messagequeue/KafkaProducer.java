package sideproject.boardservice.messagequeue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import sideproject.boardservice.board.dto.RegisterBoardDto;

@Service
@Slf4j
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public RegisterBoardDto send(String kafkaTopic, RegisterBoardDto dto) throws JsonProcessingException {
        String jsonInString = objectMapper.writeValueAsString(dto);
        kafkaTemplate.send(kafkaTopic, jsonInString);
        log.info("Kafka Producer send data from the Board microservice: {}", dto);

        return dto;
    }
}
