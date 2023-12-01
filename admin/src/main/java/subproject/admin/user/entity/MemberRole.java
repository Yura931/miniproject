package subproject.admin.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import subproject.admin.user.entity.enums.MemberRoles;

import java.util.Collections;
import java.util.Objects;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(of = { "roleId", "role" })
public class MemberRole {

    @Id
    @Column(name = "role_id")
    private UUID roleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private MemberRoles role;


    private MemberRole(Member member, MemberRoles role) {
        this.roleId = UUID.randomUUID();
        this.member = member;
        this.role = role;
    }

    public static MemberRole generateNewMemberByRoleUser(Member member) {
        return new MemberRole(member, MemberRoles.ROLE_USER);
    }

    public static MemberRole generateNewMemberByRoleAdmin(Member member) {
        MemberRole memberRole = new MemberRole(member, MemberRoles.ROLE_ADMIN);
        member.saveMemberRole(Collections.singletonList(memberRole));
        return memberRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberRole that = (MemberRole) o;
        return Objects.equals(getRoleId(), that.getRoleId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoleId());
    }
}
