package sideproject.admin.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
@Slf4j
public class MemberController {

    @GetMapping("/info")
    public String info() {
        return "개인정보";
    }

}
