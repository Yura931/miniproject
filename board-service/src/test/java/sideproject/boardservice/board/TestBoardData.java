package sideproject.boardservice.board;

import org.springframework.stereotype.Component;
import sideproject.boardservice.board.dto.RegisterBoardDto;
import sideproject.boardservice.board.dto.request.RegisterBoardRequest;
import sideproject.boardservice.board.entity.Board;
import sideproject.boardservice.board.entity.enums.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Component
public class TestBoardData {
    private Map<String, Object> firstData = new HashMap<>();
    private String key = "first";
    public List<Board> boardData(int range) {

        return IntStream.range(1, range + 1)
                .mapToObj(i -> Board.createBoard(RegisterBoardDto.from(
                                    registerBoardRequest(i))
                )).toList();
    }

    private RegisterBoardRequest registerBoardRequest(int range) {
        return new RegisterBoardRequest(
                    BoardEnabled.Y,
                    BoardVisible.Y,
                    BoardType.GENERAL,
                    "title" + range,
                    "description" + range,
                    BoardCategoryEnabled.Y,
                    BoardFileEnabled.Y,
                    BoardCommentEnabled.Y,
                    List.of("category1", "category2")
        );
    }

    public Map<String, Object> getFirstData() {
        return firstData;
    }

    public void setFirstData(String key, Object value) {
        firstData.put(key, value);
    }

    public String getKey() {
        return this.key;
    }
}
