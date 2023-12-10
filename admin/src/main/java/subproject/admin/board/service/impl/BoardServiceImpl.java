package subproject.admin.board.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import subproject.admin.board.dto.*;
import subproject.admin.board.dto.item.BoardItem;
import subproject.admin.board.dto.item.BoardPageItem;
import subproject.admin.board.dto.response.BoardPageResponse;
import subproject.admin.board.dto.response.RegisterBoardResponse;
import subproject.admin.board.dto.response.SearchBoardResponse;
import subproject.admin.board.entity.Board;
import subproject.admin.board.repository.BoardRepository;
import subproject.admin.board.service.BoardService;

import java.util.Optional;

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
        BoardPageItem item = BoardPageItem.boardEntityToDto(all);
        return new BoardPageResponse(item);
    }

    public void updateById(UpdateBoardDto dto) {
        Board findBoard = boardRepository.findById(dto.id())
                .orElseThrow(EntityNotFoundException::new);
        Board board = findBoard.updateBoard(
                dto.boardEnabled(),
                dto.boardVisible(),
                dto.boardType(),
                dto.boardTitle(),
                dto.boardDescription(),
                dto.boardCategoryEnabled(),
                dto.boardFileEnabled(),
                dto.boardCommentEnabled(),
                dto.boardReplyCommentEnabled()
        );
    }

    public void deleteById(DeleteBoardDto dto) {
        boardRepository.deleteById(dto.id());
    }
}
