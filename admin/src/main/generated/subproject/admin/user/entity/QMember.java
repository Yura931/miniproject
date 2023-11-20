package subproject.admin.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -741710154L;

    public static final QMember member = new QMember("member1");

    public final subproject.admin.common.entity.QBaseTimeEntity _super = new subproject.admin.common.entity.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final EnumPath<subproject.admin.user.entity.enums.CreateType> createType = createEnum("createType", subproject.admin.user.entity.enums.CreateType.class);

    public final StringPath email = createString("email");

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath password = createString("password");

    public final ListPath<MemberRole, QMemberRole> roles = this.<MemberRole, QMemberRole>createList("roles", MemberRole.class, QMemberRole.class, PathInits.DIRECT2);

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

