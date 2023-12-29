package sideproject.boardservice.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = 183095862L;

    public static final QBoard board = new QBoard("board");

    public final sideproject.boardservice.common.entity.QBaseEntity _super = new sideproject.boardservice.common.entity.QBaseEntity(this);

    public final EnumPath<sideproject.boardservice.board.entity.enums.BoardEnabled> boardCategoryEnabled = createEnum("boardCategoryEnabled", sideproject.boardservice.board.entity.enums.BoardEnabled.class);

    public final EnumPath<sideproject.boardservice.board.entity.enums.BoardEnabled> boardCommentEnabled = createEnum("boardCommentEnabled", sideproject.boardservice.board.entity.enums.BoardEnabled.class);

    public final StringPath boardDescription = createString("boardDescription");

    public final EnumPath<sideproject.boardservice.board.entity.enums.BoardEnabled> boardEnabled = createEnum("boardEnabled", sideproject.boardservice.board.entity.enums.BoardEnabled.class);

    public final EnumPath<sideproject.boardservice.board.entity.enums.BoardEnabled> boardFileEnabled = createEnum("boardFileEnabled", sideproject.boardservice.board.entity.enums.BoardEnabled.class);

    public final EnumPath<sideproject.boardservice.board.entity.enums.BoardEnabled> boardReplyCommentEnabled = createEnum("boardReplyCommentEnabled", sideproject.boardservice.board.entity.enums.BoardEnabled.class);

    public final StringPath boardTitle = createString("boardTitle");

    public final EnumPath<sideproject.boardservice.board.entity.enums.BoardType> boardType = createEnum("boardType", sideproject.boardservice.board.entity.enums.BoardType.class);

    public final EnumPath<sideproject.boardservice.board.entity.enums.BoardEnabled> boardVisible = createEnum("boardVisible", sideproject.boardservice.board.entity.enums.BoardEnabled.class);

    public final ListPath<BoardCategory, QBoardCategory> categories = this.<BoardCategory, QBoardCategory>createList("categories", BoardCategory.class, QBoardCategory.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final ListPath<sideproject.boardservice.post.entity.Post, sideproject.boardservice.post.entity.QPost> posts = this.<sideproject.boardservice.post.entity.Post, sideproject.boardservice.post.entity.QPost>createList("posts", sideproject.boardservice.post.entity.Post.class, sideproject.boardservice.post.entity.QPost.class, PathInits.DIRECT2);

    public QBoard(String variable) {
        super(Board.class, forVariable(variable));
    }

    public QBoard(Path<? extends Board> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoard(PathMetadata metadata) {
        super(Board.class, metadata);
    }

}

