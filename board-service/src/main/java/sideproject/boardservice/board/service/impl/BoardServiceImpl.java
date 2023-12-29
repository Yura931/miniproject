package sideproject.boardservice.board.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sideproject.boardservice.board.dto.*;
import sideproject.boardservice.board.dto.item.BoardItem;
import sideproject.boardservice.board.dto.item.BoardPageItem;
import sideproject.boardservice.board.dto.projection.SearchBoardPageDto;
import sideproject.boardservice.board.dto.response.*;
import sideproject.boardservice.board.entity.Board;
import sideproject.boardservice.board.repository.BoardCategoryRepository;
import sideproject.boardservice.board.repository.BoardRepository;
import sideproject.boardservice.board.repository.BoardRepositoryCustom;
import sideproject.boardservice.board.service.BoardService;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final BoardRepositoryCustom boardRepositoryCustom;
    private final BoardCategoryRepository boardCategoryRepository;

    @Override
    @Transactional
    public RegisterBoardResponse save(RegisterBoardDto dto) {
        Board board = Board.createBoard(dto);
        Board save = boardRepository.save(board);

        BoardItem item = BoardItem.boardEntityToDto(save);
        return new RegisterBoardResponse(item);
    }

    @Override
    @Transactional(readOnly = true)
    public SearchBoardResponse findById(SelectBoardDto dto) {
        Board board = boardRepository.findById(dto.id())
                .orElseThrow(EntityNotFoundException::new);

        BoardItem item = BoardItem.boardEntityToDto(board);
        return new SearchBoardResponse(item);
    }

    @Override
    @Transactional(readOnly = true)
    public BoardPageResponse findAll(SearchBoardDto dto) {
        // Page<Board> all = boardRepository.findAll(pageable);
        Page<SearchBoardPageDto> searchBoardPageDtos = boardRepositoryCustom.searchAll(dto);
        BoardPageItem item = BoardPageItem.boardEntityToDto(searchBoardPageDtos);
        return new BoardPageResponse(item);
    }

    @Transactional
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

    @Transactional
    public void deleteById(DeleteBoardDto dto) {
        boardRepository.deleteById(dto.id());
    }

    @Transactional
    public RegisterBoardResponse insertCategoryByBoardId(RegisterBoardCategoryDto dto) {
        Board board = this.getBoard(dto.boardId());
        board.addBoardCategory(BoardCategoryDto.from(dto.categoryName()));
        BoardItem boardItem = BoardItem.boardEntityToDto(board);
        return new RegisterBoardResponse(boardItem);
    }

    @Transactional
    public UpdateBoardResponse updateCategoryByBoardId(UpdateBoardCategoryDto dto) {
        Board board = this.getBoard(dto.boardId());
        board.getCategories().stream()
                .filter((categories) -> categories.getId().equals(dto.boardCategoryId()))
                .map((category) -> category.updateCategory(dto.boardCategoryName()))
                .toList();

        BoardItem boardItem = BoardItem.boardEntityToDto(board);
        return new UpdateBoardResponse(boardItem);
    }

    private Board getBoard(Long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public DeleteBoardResponse deleteCategoryById(DeleteBoardCategoryDto dto) {
        boardCategoryRepository.deleteByIdAndBoardId(dto.categoryId(), dto.boardId());
        Board board = this.getBoard(dto.boardId());
        board.deleteBoardCategory(dto.categoryId());
        BoardItem boardItem = BoardItem.boardEntityToDto(board);
        return new DeleteBoardResponse(boardItem);
    }
}
