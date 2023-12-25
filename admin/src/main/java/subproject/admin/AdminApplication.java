package subproject.admin;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import subproject.admin.board.dto.record.RegisterBoardDto;
import subproject.admin.board.dto.request.RegisterBoardRequest;
import subproject.admin.board.entity.Board;
import subproject.admin.board.entity.BoardCategory;
import subproject.admin.board.entity.enums.BoardType;
import subproject.admin.board.entity.enums.Enabled;
import subproject.admin.board.repository.BoardCategoryRepository;
import subproject.admin.board.repository.BoardRepository;
import subproject.admin.file.dto.FileDto;
import subproject.admin.post.dto.record.RegisterPostDto;
import subproject.admin.post.dto.request.RegisterPostRequest;
import subproject.admin.post.entity.Post;
import subproject.admin.post.repository.PostRepository;
import subproject.admin.user.entity.Member;
import subproject.admin.user.entity.MemberRole;
import subproject.admin.user.repository.MemberRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootApplication
@EnableCaching
public class AdminApplication implements CommandLineRunner {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private BoardCategoryRepository boardCategoryRepository;

	@Autowired
	private EntityManager em;

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

		IntStream.rangeClosed(1, 2)
				.forEach(value -> {
					RegisterBoardDto registerBoardDto = RegisterBoardDto.from(
							new RegisterBoardRequest(
									Enabled.Y,
									Enabled.Y,
									BoardType.GENERAL,
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

		final Board board = boardRepository.findById(1L).get();
		BoardCategory boardCategory = board.getCategories().get(0);
		BoardCategory boardCategory2 = board.getCategories().get(1);

		IntStream.rangeClosed(1, 5)
				.forEach(value -> {
					RegisterPostDto registerPostDto = RegisterPostDto.of(board.getId(), new RegisterPostRequest("title" + value, "content" + value, boardCategory.getId()), 0L, new ArrayList<>());
					Post post = Post.createPost(registerPostDto, board, boardCategory);
					postRepository.save(post);
				});

		IntStream.rangeClosed(1, 5)
				.forEach(value -> {
					RegisterPostDto registerPostDto = RegisterPostDto.of(board.getId(), new RegisterPostRequest("title" + value, "content" + value, boardCategory2.getId()), 0L, new ArrayList<>());
					Post post = Post.createPost(registerPostDto, board, boardCategory2);
					postRepository.save(post);
				});
	}
}
