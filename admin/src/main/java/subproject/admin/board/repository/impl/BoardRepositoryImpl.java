package subproject.admin.board.repository.impl;

import com.querydsl.core.types.Projections;
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
import subproject.admin.board.dto.SearchBoardDto;
import subproject.admin.board.dto.projection.SearchBoardPageDto;
import subproject.admin.board.entity.Board;
import subproject.admin.board.entity.enums.Enabled;
import subproject.admin.board.repository.BoardRepositoryCustom;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = queryFactory
                .select(board.count())
                .from(board)
                .where();

        return PageableExecutionUtils.getPage(fetch, pageable, count::fetchOne);
    }

    @RequiredArgsConstructor
    @Getter
    public static class TestDto {
        private final UUID id;
        private final Enabled enabled;
        private final Enabled visible;
        private final String type;
        private final String title;
        private final LocalDateTime createAt;
    }
}
