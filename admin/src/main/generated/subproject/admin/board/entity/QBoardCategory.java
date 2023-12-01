package subproject.admin.board.entity;

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

    private static final long serialVersionUID = 1701220757L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoardCategory boardCategory = new QBoardCategory("boardCategory");

    public final subproject.admin.common.entity.QBaseTimeEntity _super = new subproject.admin.common.entity.QBaseTimeEntity(this);

    public final StringPath boardCategoryName = createString("boardCategoryName");

    public final QBoardMaster boardMaster;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

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
        this.boardMaster = inits.isInitialized("boardMaster") ? new QBoardMaster(forProperty("boardMaster")) : null;
    }

}

