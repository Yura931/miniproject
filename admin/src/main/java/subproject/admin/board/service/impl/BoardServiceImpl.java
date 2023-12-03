package subproject.admin.board.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import subproject.admin.board.dto.BoardPageDto;
import subproject.admin.board.dto.RegisterBoardDto;
import subproject.admin.board.dto.SearchBoardDto;
import subproject.admin.board.dto.item.BoardItem;
import subproject.admin.board.dto.response.BoardPageResponse;
import subproject.admin.board.dto.response.RegisterBoardResponse;
import subproject.admin.board.dto.response.SearchBoardResponse;
import subproject.admin.board.entity.Board;
import subproject.admin.board.entity.BoardCategory;
import subproject.admin.board.repository.BoardRepository;
import subproject.admin.board.service.BoardService;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    @Override
    public RegisterBoardResponse save(RegisterBoardDto dto) {
        Board board = Board.createBoard(dto);
        Board save = boardRepository.save(board);

        BoardItem item = BoardItem.boardEntityToDto(save);
        return new RegisterBoardResponse(item);
    }

    @Override
    public SearchBoardResponse findById(SearchBoardDto dto) {
        Board board = boardRepository.findById(dto.id())
                .orElseThrow(EntityNotFoundException::new);

        BoardItem item = BoardItem.boardEntityToDto(board);
        return new SearchBoardResponse(item);
    }

    @Override
    public BoardPageResponse findAll(BoardPageDto dto) {
        Pageable pageable = PageRequest.of(dto.pageNo(), dto.pageSize());

        Page<Board> all = boardRepository.findAll(pageable);
        return new BoardPageResponse(all);
    }
}
