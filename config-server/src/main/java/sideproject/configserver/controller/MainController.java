package sideproject.configserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    // 컨테이너 실행 시 다른 서비스에서 http://config-service:8888 uri 요청을 받기위해 기본경로 설정 필요
    // 해주지 않으면 에러가 발생한다.
    @RequestMapping("/")
    public String main() {
        return "Welcome Page";
    }
}
