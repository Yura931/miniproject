package sideproject.authservice.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sideproject.authservice.auth.dto.SignUpDto;
import sideproject.authservice.member.enums.AccountType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member {

    @Id
    @Column(name = "member_id", length = 32)
    private UUID id;

    private String email;

    private String password;

    private String nickname;

    @Enumerated(value = EnumType.STRING)
    private AccountType accountType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<MemberRole> roles = new ArrayList<>();

    private Member(UUID userId, String email, String nickname, String password, AccountType accountType) {
        this.id = userId;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.accountType = accountType;
    }

    public static Member createUser(SignUpDto dto) {
        UUID uuid = UUID.randomUUID();
        return new Member(
                uuid,
                dto.email(),
                dto.password(),
                dto.nickname(),
                dto.accountType()
        );
    }

    public void saveUserRole(List<MemberRole> memberRoles) {
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
