package sideproject.authservice.auth.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import sideproject.authservice.user.entity.Users;

import java.util.Optional;
import java.util.UUID;

public interface AuthRepository extends JpaRepository<Users, UUID> {

    @EntityGraph(attributePaths = "roles")
    Optional<Users> findOneWithRoleByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);
}
