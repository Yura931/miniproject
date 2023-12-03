package subproject.admin.user.service;

import org.springframework.data.jpa.repository.EntityGraph;
import subproject.admin.user.entity.Member;

import java.util.Optional;

public interface MemberService {

    Optional<Member> findOneWithRoleByEmail(String email);

    boolean existsByEmail(String email);
}
