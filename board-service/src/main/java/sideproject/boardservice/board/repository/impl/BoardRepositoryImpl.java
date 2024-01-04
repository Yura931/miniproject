package sideproject.boardservice.board.repository.impl;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import sideproject.boardservice.board.dto.SearchBoardDto;
import sideproject.boardservice.board.dto.enums.BoardSortCondition;
import sideproject.boardservice.board.dto.projection.SearchBoardPageDto;
import sideproject.boardservice.board.dto.projection.BoardSelectorDto;
import sideproject.boardservice.board.entity.Board;
import sideproject.boardservice.board.repository.BoardRepositoryCustom;

import java.util.ArrayList;
import java.util.List;

import static sideproject.boardservice.board.entity.QBoard.board;

@Repository
public class BoardRepositoryImpl extends QuerydslRepositorySupport implements BoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public BoardRepositoryImpl(EntityManager entityManager) {
        super(Board.class);
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public List<BoardSelectorDto> boardSelector() {
        List<BoardSelectorDto> fetch = queryFactory
                .select(Projections.constructor(
                        BoardSelectorDto.class,
                        board.id,
                        board.boardTitle
                ))
                .from(board)
                .fetch();

        return fetch;
    }

    public Page<SearchBoardPageDto> searchAll(SearchBoardDto dto) {
        OrderSpecifier[] sortCondition = getSortCondition(dto);

        List<SearchBoardPageDto> fetch = queryFactory
                .select(Projections.constructor(
                        SearchBoardPageDto.class,
                        board.id,
                        board.boardEnabled,
                        board.boardVisible,
                        board.boardType,
                        board.boardTitle,
                        board.createDate,
                        board.createdBy
                ))
                .from(board)
                .where()
                .orderBy(sortCondition)
                .offset(dto.pageable().getOffset())
                .limit(dto.pageable().getPageSize())
                .fetch();

        JPAQuery<Long> count = queryFactory
                .select(board.count())
                .from(board)
                .where();

        return PageableExecutionUtils.getPage(fetch, dto.pageable(), count::fetchOne);
    }

    private OrderSpecifier[] getSortCondition(SearchBoardDto dto) {
        final List<OrderSpecifier> orders = new ArrayList<>();
        addOrderSpecifierByCurrentSortCondition(dto, orders);
        if(orders.isEmpty()) {
            return new OrderSpecifier[]{new OrderSpecifier(Order.DESC, board.createDate)};
        }
        else {
            return orders.toArray(new OrderSpecifier[0]);
        }
    }

    private void addOrderSpecifierByCurrentSortCondition(final SearchBoardDto dto, final List<OrderSpecifier> specifiers) {
        Order order = Order.valueOf(dto.sortDirection().name());
        BoardSortCondition condition = dto.boardSortCondition();
        switch (condition) {
            case CREATE_AT:
                specifiers.add(new OrderSpecifier(order, board.createDate));
                return;
            case CREATE_BY:
                specifiers.add(new OrderSpecifier(order, board.createdBy));
                return;
            case BOARD_TYPE:
                specifiers.add(new OrderSpecifier(order, board.boardType));
                return;
        }
    }



}
