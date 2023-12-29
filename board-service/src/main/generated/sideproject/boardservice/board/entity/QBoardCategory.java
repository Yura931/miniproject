package sideproject.boardservice.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoardCategory is a Querydsl query type for BoardCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardCategory extends EntityPathBase<BoardCategory> {

    private static final long serialVersionUID = -2109448876L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoardCategory boardCategory = new QBoardCategory("boardCategory");

    public final QBoard board;

    public final StringPath categoryName = createString("categoryName");

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final ListPath<sideproject.boardservice.post.entity.Post, sideproject.boardservice.post.entity.QPost> post = this.<sideproject.boardservice.post.entity.Post, sideproject.boardservice.post.entity.QPost>createList("post", sideproject.boardservice.post.entity.Post.class, sideproject.boardservice.post.entity.QPost.class, PathInits.DIRECT2);

    public QBoardCategory(String variable) {
        this(BoardCategory.class, forVariable(variable), INITS);
    }

    public QBoardCategory(Path<? extends BoardCategory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoardCategory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoardCategory(PathMetadata metadata, PathInits inits) {
        this(BoardCategory.class, metadata, inits);
    }

    public QBoardCategory(Class<? extends BoardCategory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new QBoard(forProperty("board")) : null;
    }

}

