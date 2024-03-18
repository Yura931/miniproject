package sideproject.boardservice.feign;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FileServiceClientTest {

    @Autowired
    FileServiceClient fileServiceClient;

    @Test
    @DisplayName("")
    void fileServiceRequestTest() {
        fileServiceClient.files(UUID.randomUUID(), "USERS");
    }
}