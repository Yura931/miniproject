package sideproject.admin.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import sideproject.admin.common.entity.BaseTimeEntity;
import sideproject.admin.user.entity.enums.AccountType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = { "id", "email", "password", "createType"})
public class Member extends BaseTimeEntity {

    @Id
    @Column(name = "member_id", columnDefinition = "BINARY(16)")
    private UUID id;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<MemberRole> roles = new ArrayList<>();

    @Builder(access = AccessLevel.PRIVATE)
    private Member(UUID memberId, String email, String password, AccountType accountType) {
        this.id = memberId;
        this.email = email;
        this.password = password;
        this.accountType = accountType;
    }

    public static Member joinNewMember(String email, String password) {
        UUID uuid = UUID.randomUUID();
        return Member.builder()
                .memberId(uuid)
                .email(email)
                .password(password)
                .accountType(AccountType.EMAIL)
                .build();
    }

    public static Member joinNewAdminMember(String email, String password) {
        UUID uuid = UUID.randomUUID();
        Member build = Member.builder()
                .memberId(uuid)
                .email(email)
                .password(password)
                .accountType(AccountType.EMAIL)
                .build();
        MemberRole.generateNewMemberByRoleAdmin(build);
        return build;
    }

    public void saveMemberRole(List<MemberRole> memberRoles) {
        this.roles = memberRoles
                .stream()
                .filter(role -> role.getMember().id.equals(this.id))
                .toList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member that = (Member) o;
        return Objects.equals(this.getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }
}
