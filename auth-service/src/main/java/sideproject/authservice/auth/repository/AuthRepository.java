package sideproject.authservice.auth.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import sideproject.authservice.member.entity.Member;

import java.util.Optional;
import java.util.UUID;

public interface AuthRepository extends JpaRepository<Member, UUID> {

    @EntityGraph(attributePaths = "roles")
    Optional<Member> findOneWithRoleByEmail(String email);

    boolean existsByEmail(String email);
}
