package subproject.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import subproject.admin.board.dto.RegisterBoardDto;
import subproject.admin.board.dto.request.RegisterBoardRequest;
import subproject.admin.board.entity.Board;
import subproject.admin.board.entity.enums.Enabled;
import subproject.admin.board.repository.BoardRepository;
import subproject.admin.user.entity.Member;
import subproject.admin.user.entity.MemberRole;
import subproject.admin.user.repository.MemberRepository;

import java.util.Arrays;
import java.util.stream.IntStream;

@SpringBootApplication
@EnableCaching
@EnableJpaAuditing
public class AdminApplication implements CommandLineRunner {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private BoardRepository boardRepository;

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

		IntStream.rangeClosed(1, 20)
				.forEach(value -> {
					RegisterBoardDto registerBoardDto = RegisterBoardDto.from(
							new RegisterBoardRequest(
									Enabled.Y,
									Enabled.Y,
									"A",
									"title",
									"description",
									Enabled.Y,
									Enabled.Y,
									Enabled.Y,
									Enabled.Y,
									Arrays.asList("category1", "category2")
							)
					);
					Board board = Board.createBoard(registerBoardDto);
					boardRepository.save(board);
				});
	}
}
