package sideproject.admin.user.service;

import sideproject.admin.user.entity.Member;

import java.util.Optional;

public interface MemberService {

    Optional<Member> findOneWithRoleByEmail(String email);

    boolean existsByEmail(String email);
}
