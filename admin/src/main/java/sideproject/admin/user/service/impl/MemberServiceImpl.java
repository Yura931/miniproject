package sideproject.admin.user.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sideproject.admin.user.entity.Member;
import sideproject.admin.user.repository.MemberRepository;
import sideproject.admin.user.service.MemberService;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Optional<Member> findOneWithRoleByEmail(String email) {
        return memberRepository.findOneWithRoleByEmail(email);
    }

    @Override
    public boolean existsByEmail(String email) {
        return existsByEmail(email);
    }
}
