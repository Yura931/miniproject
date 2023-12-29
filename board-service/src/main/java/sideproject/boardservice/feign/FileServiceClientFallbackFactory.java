package sideproject.boardservice.feign;

import org.springframework.cloud.openfeign.FallbackFactory;

public class FileServiceClientFallbackFactory implements FallbackFactory {

    @Override
    public FileServiceClient create(Throwable cause) {
        return null;
    }
}
