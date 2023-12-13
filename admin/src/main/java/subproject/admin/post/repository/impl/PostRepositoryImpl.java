package subproject.admin.post.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import subproject.admin.board.entity.Board;
import subproject.admin.post.repository.PostRepositoryCustom;

public class PostRepositoryImpl extends QuerydslRepositorySupport implements PostRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public PostRepositoryImpl(EntityManager entityManager) {
        super(Board.class);
        this.queryFactory = new JPAQueryFactory(entityManager);
    }
}
