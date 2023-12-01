package subproject.admin.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoardMaster is a Querydsl query type for BoardMaster
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardMaster extends EntityPathBase<BoardMaster> {

    private static final long serialVersionUID = -717539143L;

    public static final QBoardMaster boardMaster = new QBoardMaster("boardMaster");

    public final subproject.admin.common.entity.QBaseEntity _super = new subproject.admin.common.entity.QBaseEntity(this);

    public final ListPath<BoardCategory, QBoardCategory> boardCategory = this.<BoardCategory, QBoardCategory>createList("boardCategory", BoardCategory.class, QBoardCategory.class, PathInits.DIRECT2);

    public final EnumPath<Enabled> boardCategoryEnabled = createEnum("boardCategoryEnabled", Enabled.class);

    public final EnumPath<Enabled> boardCommentEnabled = createEnum("boardCommentEnabled", Enabled.class);

    public final EnumPath<Enabled> boardEnabled = createEnum("boardEnabled", Enabled.class);

    public final EnumPath<Enabled> boardFileEnabled = createEnum("boardFileEnabled", Enabled.class);

    public final StringPath boardMasterDescription = createString("boardMasterDescription");

    public final StringPath boardMasterTitle = createString("boardMasterTitle");

    public final StringPath boardReadPermission = createString("boardReadPermission");

    public final EnumPath<Enabled> boardReplyCommentEnabled = createEnum("boardReplyCommentEnabled", Enabled.class);

    public final StringPath boardType = createString("boardType");

    public final EnumPath<Enabled> boardVisible = createEnum("boardVisible", Enabled.class);

    public final StringPath boardWritePermission = createString("boardWritePermission");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public QBoardMaster(String variable) {
        super(BoardMaster.class, forVariable(variable));
    }

    public QBoardMaster(Path<? extends BoardMaster> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoardMaster(PathMetadata metadata) {
        super(BoardMaster.class, metadata);
    }

}

