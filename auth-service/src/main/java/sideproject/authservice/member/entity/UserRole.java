package sideproject.authservice.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import sideproject.authservice.member.enums.UserRoles;

import java.util.Collections;
import java.util.Objects;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(of = { "roleId", "role" })
public class UserRole {

    @Id
    @Column(name = "role_id", length = 32)
    private UUID roleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRoles role;


    private UserRole(Users users, UserRoles role) {
        this.roleId = UUID.randomUUID();
        this.users = users;
        this.role = role;
    }

    public static UserRole generateNewMemberByRoleUser(Users users) {
        return new UserRole(users, UserRoles.ROLE_USER);
    }

    public static UserRole generateNewMemberByRoleAdmin(Users users) {
        UserRole userRole = new UserRole(users, UserRoles.ROLE_ADMIN);
        users.saveUserRole(Collections.singletonList(userRole));
        return userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole that = (UserRole) o;
        return Objects.equals(getRoleId(), that.getRoleId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoleId());
    }
}
