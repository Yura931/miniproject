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
import sideproject.boardservice.board.dto.item.BoardIdItem;
import sideproject.boardservice.board.dto.projection.SearchBoardPageDto;
import sideproject.boardservice.board.dto.response.*;
import sideproject.boardservice.board.entity.Board;
import sideproject.boardservice.board.entity.BoardCategory;
import sideproject.boardservice.board.repository.BoardCategoryRepository;
import sideproject.boardservice.board.repository.BoardRepository;
import sideproject.boardservice.board.repository.BoardRepositoryCustom;
import sideproject.boardservice.board.service.BoardService;
import sideproject.boardservice.messagequeue.KafkaProducer;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final BoardRepositoryCustom boardRepositoryCustom;
    private final KafkaProducer kafkaProducer;

    @Override
    @Transactional
    public BoardIdResponse boardId() {
        BoardIdItem boardIdItem = BoardIdItem.boardEntityToDto(boardRepositoryCustom.boardSelector());
        return new BoardIdResponse(boardIdItem);
    }

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
        Board board = this.getBoard(dto.id());

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
    @Override
    @Transactional
    public UpdateBoardResponse updateById(UpdateBoardDto dto) {
        Board findBoard = this.getBoard(dto.id());
        Board board = findBoard.updateBoard(dto);

        BoardItem boardItem = BoardItem.boardEntityToDto(board);
        return new UpdateBoardResponse(boardItem);
    }
    @Override
    @Transactional
    public void deleteById(DeleteBoardDto dto) {
        boardRepository.findById(dto.id())
                .stream().findFirst()
                .ifPresentOrElse(board -> boardRepository.deleteById(board.getId()),
                        () -> new EntityNotFoundException());
    }
    @Override
    @Transactional
    public void insertCategoryByBoardId(RegisterBoardCategoryDto dto) {
        Board board = this.getBoard(dto.boardId());
        BoardCategory boardCategory = BoardCategory.createCategory(board, BoardCategoryDto.from(dto.categoryName()));
        board.addBoardCategory(boardCategory);
    }
    @Override
    @Transactional
    public void updateCategoryByBoardId(UpdateBoardCategoryDto dto) {
        Board board = this.getBoard(dto.boardId());
        board.getCategories()
                .stream()
                .filter(categories -> categories.getId().equals(dto.boardCategoryId()))
                .findFirst()
                .ifPresent(category -> category.updateCategory(dto.boardCategoryName()));
    }

    @Override
    @Transactional
    public void deleteCategoryById(DeleteBoardCategoryDto dto) {
        Board board = this.getBoard(dto.boardId());
        board.deleteBoardCategory(dto.categoryId());
    }

    private Board getBoard(Long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(EntityNotFoundException::new);
    }
}
