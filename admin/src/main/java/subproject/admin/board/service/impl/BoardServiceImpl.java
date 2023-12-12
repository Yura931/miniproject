package subproject.admin.board.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import subproject.admin.board.dto.item.BoardItem;
import subproject.admin.board.dto.item.BoardPageItem;
import subproject.admin.board.dto.projection.SearchBoardPageDto;
import subproject.admin.board.dto.record.*;
import subproject.admin.board.dto.response.BoardPageResponse;
import subproject.admin.board.dto.response.RegisterBoardResponse;
import subproject.admin.board.dto.response.SearchBoardResponse;
import subproject.admin.board.dto.response.UpdateBoardResponse;
import subproject.admin.board.entity.Board;
import subproject.admin.board.entity.BoardCategoryMapping;
import subproject.admin.board.repository.BoardRepository;
import subproject.admin.board.repository.BoardRepositoryCustom;
import subproject.admin.board.service.BoardService;

@Service
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService {
    private BoardRepository boardRepository;
    private BoardRepositoryCustom boardRepositoryCustom;

    @Override
    public RegisterBoardResponse save(RegisterBoardDto dto) {
        Board board = Board.createBoard(dto);
        Board save = boardRepository.save(board);

        BoardItem item = BoardItem.boardEntityToDto(save);
        return new RegisterBoardResponse(item);
    }

    @Override
    public SearchBoardResponse findById(DetailBoardDto dto) {
        Board board = boardRepository.findById(dto.id())
                .orElseThrow(EntityNotFoundException::new);

        BoardItem item = BoardItem.boardEntityToDto(board);
        return new SearchBoardResponse(item);
    }

    @Override
    public BoardPageResponse findAll(SearchBoardDto dto) {
        Pageable pageable = PageRequest.of(dto.pageNo(), dto.pageSize());
        // Page<Board> all = boardRepository.findAll(pageable);
        Page<SearchBoardPageDto> searchBoardPageDtos = boardRepositoryCustom.searchAll(dto, pageable);
        BoardPageItem item = BoardPageItem.boardEntityToDto(searchBoardPageDtos);
        return new BoardPageResponse(item);
    }

    public UpdateBoardResponse updateById(UpdateBoardDto dto) {
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

        BoardItem boardItem = BoardItem.boardEntityToDto(board);
        return new UpdateBoardResponse(boardItem);
    }

    public void updateCategoryById(UpdateBoardCategoryDto dto) {
        Board board = boardRepository.findById(dto.boardId())
                .orElseThrow(EntityNotFoundException::new);
        BoardCategoryMapping boardCategoryMapping = board
                        .getBoardCategoryMapping()
                        .updateBoardCategoryMapping(dto.boardCategoryId(), dto.boardCategoryName());
    }

    public void deleteById(DeleteBoardDto dto) {
        boardRepository.deleteById(dto.id());
    }

    public void deleteCategoryById(DeleteBoardCategoryDto dto) {
        Board board = boardRepository.findById(dto.boardId())
                .orElseThrow(EntityNotFoundException::new);
        BoardCategoryMapping boardCategoryMapping = board.getBoardCategoryMapping();
        boardRepository.deleteByBoardCategoryMappingAndCategoryId(boardCategoryMapping, dto.categoryId());
    }
}
