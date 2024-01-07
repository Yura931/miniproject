package sideproject.admin.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class S3Config {

    @Value("")
    private String accessKey;

    @Value("")
    private String secretKey;
    private String infoPath = "C:\\dev\\aws\\info.txt";


    @Bean
    AmazonS3 amazonS3Client() {
        String accKey = "";
        String secKey = "";
        try {
            Map<String, String> infoMap = bucketInfo();
            accKey = infoMap.get("accessKey");
            secKey = infoMap.get("secretKey");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return AmazonS3Client.builder()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accKey, secKey)))
                .withRegion(Regions.AP_NORTHEAST_2)
                .build();
    }

    // local 용도
    private Map<String, String> bucketInfo() throws IOException {
        String readText = Files.readString(Paths.get(infoPath));
        Map<String, String> keyValueMap = new HashMap<>();

        String[] pairs = readText.split(",");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            if (keyValue.length == 2) {
                keyValueMap.put(keyValue[0], keyValue[1]);
            }
        }
        return keyValueMap;
    }

    @Bean("bucketInfo")
    Map<String, String> bucket() throws IOException {
        return bucketInfo();
    }
}
