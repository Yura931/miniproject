package subproject.admin.user.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import subproject.admin.user.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
    @EntityGraph(attributePaths = "roles")
    Optional<Member> findOneWithRoleByEmail(String email);
    boolean existsByEmail(String email);
}
