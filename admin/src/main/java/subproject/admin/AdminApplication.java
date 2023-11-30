package subproject.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import subproject.admin.user.entity.Member;
import subproject.admin.user.entity.MemberRole;
import subproject.admin.user.repository.MemberRepository;

@SpringBootApplication
public class AdminApplication implements CommandLineRunner {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Member member = Member.joinNewAdminMember(
				"admin@gmail.com",
				new BCryptPasswordEncoder().encode("admin")
		);

		MemberRole memberRole = MemberRole.generateNewMemberByRoleAdmin(member);
		memberRepository.save(member);
	}
}
