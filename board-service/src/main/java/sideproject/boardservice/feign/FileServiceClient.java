package sideproject.boardservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@FeignClient(name = "file-service")
public interface FileServiceClient {
    @RequestMapping(value = "/file-service/api/v1/file/register", method = RequestMethod.POST)
    ResponseEntity fileRegister(MultipartHttpServletRequest request);
}
