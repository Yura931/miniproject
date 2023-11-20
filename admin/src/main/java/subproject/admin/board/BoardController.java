package subproject.admin.board;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import subproject.admin.common.dto.Result;
import subproject.admin.common.dto.ResultHandler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
public class BoardController {

    @PreAuthorize("hasRole(ROLE_ADMIN)")
    @GetMapping("/board/list")
    public Result<?> boardList() {
        return ResultHandler.handle(HttpStatus.OK.value(), "게시판목록",
                IntStream.rangeClosed(1, 10)
                        .mapToObj(i -> Map.of(
                                "id", i,
                                "title", "제목" + i,
                                "content", "내용" + i
                        ))
                        .collect(Collectors.toList())
                );
    }
}
