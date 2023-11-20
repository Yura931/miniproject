package subproject.admin.user.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import subproject.admin.user.repository.MemberRepository;

import java.util.Arrays;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원저장() throws Exception {
        Member member = Member.joinNewAdminMember("test@email.com", passwordEncoder.encode("test1234"));
        memberRepository.save(member);

        em.flush();
        em.clear();

        Member findMember = em.find(Member.class, member.getId());
        assertThat(findMember.getId()).isEqualTo(member.getId());
    }

    @Test
    public void memberole테스트() throws Exception {
        Member member = Member.joinNewAdminMember("test@email.com", "test1234");
        MemberRole memberAdminRole = MemberRole.generateNewMemberByRoleAdmin(member);
        MemberRole memberUserRole = MemberRole.generateNewMemberByRoleUser(member);
        member.saveMemberRole(Arrays.asList(memberUserRole, memberAdminRole));
        System.out.println("member = " + member.getRoles());
    }
}