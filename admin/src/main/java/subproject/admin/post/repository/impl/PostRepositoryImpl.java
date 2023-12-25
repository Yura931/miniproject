package subproject.admin.post.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import subproject.admin.post.dto.record.SearchPostDto;
import subproject.admin.post.entity.Post;
import subproject.admin.post.projection.SearchPostPageDto;
import subproject.admin.post.repository.PostRepositoryCustom;

import java.util.List;
import java.util.Objects;

import static subproject.admin.board.entity.QBoardCategory.boardCategory;
import static subproject.admin.post.entity.QPost.post;

@Service
public class PostRepositoryImpl extends QuerydslRepositorySupport implements PostRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public PostRepositoryImpl(EntityManager entityManager) {
        super(Post.class);
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Page<SearchPostPageDto> searchAll(SearchPostDto dto) {

        List<SearchPostPageDto> fetch = queryFactory
                .select(Projections.constructor(
                        SearchPostPageDto.class,
                        post.id,
                        boardCategory.categoryName,
                        post.postTitle,
                        post.postContent,
                        post.createDate,
                        post.createdBy
                ))
                .from(post)
                .leftJoin(boardCategory)
                .on(post.boardCategory.eq(boardCategory))
                .offset(dto.pageable().getOffset())
                .limit(dto.pageable().getPageSize())
                .fetch();

        JPAQuery<Long> count = queryFactory
                .select(post.count())
                .from(post)
                .leftJoin(boardCategory)
                .on(post.boardCategory.eq(boardCategory))
                .where();

        return PageableExecutionUtils.getPage(fetch, dto.pageable(), count::fetchOne);
    }

    private BooleanExpression boardEq(Long boardId) {
        return !Objects.isNull(boardId) ? post.board.id.eq(boardId) : null;
    }
}
