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
import java.util.Collection;
import java.util.Collections;
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
    public void memberRole테스트() throws Exception {
        Member member = Member.joinNewAdminMember("test@email.com", "test1234");
        Member saveMember = memberRepository.save(member);
        saveMember.getRoles().stream().forEach(System.out::println);
        member.getRoles().stream().forEach(System.out::println);
    }
}