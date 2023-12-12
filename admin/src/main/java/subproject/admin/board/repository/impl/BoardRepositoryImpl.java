package subproject.admin.board.repository.impl;

import com.querydsl.core.types.*;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import subproject.admin.board.dto.enums.BoardSortCondition;
import subproject.admin.board.dto.record.SearchBoardDto;
import subproject.admin.board.dto.projection.SearchBoardPageDto;
import subproject.admin.board.entity.Board;
import subproject.admin.board.entity.enums.BoardType;
import subproject.admin.board.entity.enums.Enabled;
import subproject.admin.board.repository.BoardRepositoryCustom;
import subproject.admin.common.enums.SortDirection;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

import static subproject.admin.board.entity.QBoard.board;

@Repository
public class BoardRepositoryImpl extends QuerydslRepositorySupport implements BoardRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public BoardRepositoryImpl(EntityManager entityManager) {
        super(Board.class);
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public Page<SearchBoardPageDto> searchAll(SearchBoardDto condition, Pageable pageable) {
        List<SearchBoardPageDto> fetch = queryFactory
                .select(Projections.constructor(
                        SearchBoardPageDto.class,
                        board.id,
                        board.boardEnabled,
                        board.boardVisible,
                        board.boardType,
                        board.boardTitle,
                        board.createDate
                ))
                .from(board)
                .where()
                .orderBy(createOrderSpecifier(condition))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();



        JPAQuery<Long> count = queryFactory
                .select(board.count())
                .from(board)
                .where();



        return PageableExecutionUtils.getPage(fetch, pageable, count::fetchOne);
    }

    private OrderSpecifier[] createOrderSpecifier(SearchBoardDto searchBoardDto) {
        return Optional.ofNullable(searchBoardDto)
                .map((dto) -> createOrderSpecifierForCondition(dto))
                .orElseGet(() -> new OrderSpecifier[]{new OrderSpecifier(Order.DESC, Expressions.dateTimeOperation(LocalDateTime.class, Ops.DateTimeOps.ADD_SECONDS, board.createDate))});
    }

    private OrderSpecifier[] createOrderSpecifierForCondition(SearchBoardDto searchBoardDto) {
        Order order = Order.valueOf(Order.class, searchBoardDto.sortDirection().name());
        return new OrderSpecifier[]{new OrderSpecifier(order, getFieldForCondition(searchBoardDto))};
    }

    private Expression getFieldForCondition(SearchBoardDto searchBoardDto) {
        switch (searchBoardDto.sortCondition()) {
            case CREATE_AT -> {
                return Expressions.dateTimeOperation(LocalDateTime.class, Ops.DateTimeOps.ADD_SECONDS, board.createDate);
            }
            case CREATE_BY -> {
                return board.createdBy;
            }
            case BOARD_TYPE -> {
                return board.boardType;
            }
        }
        return null;
    }




}
