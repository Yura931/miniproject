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
public class Users {

    @Id
    @Column(name = "user_id")
    private UUID id;

    @Column(nullable = false, length = 50)
    private String email;
    @Column(nullable = false, unique = true)
    private String password;
    @Column(nullable = false, length = 50)
    private String nickname;

    @Enumerated(value = EnumType.STRING)
    private AccountType accountType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "users", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<UserRole> roles = new ArrayList<>();

    private Users(UUID userId, String email, String nickname, String password, AccountType accountType) {
        this.id = userId;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.accountType = accountType;
    }

    public static Users createUser(SignUpDto dto) {
        UUID uuid = UUID.randomUUID();
        return new Users(
                uuid,
                dto.email(),
                dto.password(),
                dto.nickname(),
                dto.accountType()
        );
    }

    public void saveUserRole(List<UserRole> userRoles) {
        this.roles = userRoles
                .stream()
                .filter(role -> role.getUsers().id.equals(this.id))
                .toList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users that = (Users) o;
        return Objects.equals(this.getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }
}
