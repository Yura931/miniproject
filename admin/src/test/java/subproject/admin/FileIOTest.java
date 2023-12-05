package subproject.admin;


import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileIOTest {
    String accessFile = "C:\\dev\\aws\\accessKey.txt";
    String secretFile = "C:\\dev\\aws\\secretKey.txt";
    String awsInfo = "C:\\dev\\aws\\info.txt";

    @Test
    public void test() throws IOException {


        FileReader reader = new FileReader(accessFile);
        int ch;
        while ((ch = reader.read()) != -1) {
            System.out.print((char) ch);
        }
    }

    @Test
    public void bufferedReaderTest() throws IOException {
        BufferedReader reader = new BufferedReader(
                new FileReader(accessFile)
        );

        String str;
        while((str = reader.readLine()) != null) {
            System.out.println(str);
        }

        reader.close();
    }

    @Test
    public void filesTest() throws IOException {
        String lines = Files.readString(Paths.get(awsInfo));
        Map<String, String> stringStringMap = infoParser(lines);
        System.out.println("stringStringMap = " + stringStringMap);
    }

    private Map<String, String> infoParser(String input) {
        Map<String, String> keyValueMap = new HashMap<>();

        String[] pairs = input.split(",");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            if (keyValue.length == 2) {
                keyValueMap.put(keyValue[0], keyValue[1]);
            }
        }

        return keyValueMap;
    }

}
