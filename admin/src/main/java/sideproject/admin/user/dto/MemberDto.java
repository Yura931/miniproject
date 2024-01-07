package sideproject.admin.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import sideproject.admin.user.entity.Member;

public class MemberDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @ToString(of = { "email", "password" })
    public static class MemberRequestDto {
        @NotNull
        @NotBlank(message = "email을 입력해주세요.")
        @Size(min = 3, max = 50)
        private String email;

        @NotNull
        @NotBlank(message = "password를 입력해주세요.")
        @Size(min = 3, max = 100)
        private String password;

        @Builder
        public MemberRequestDto(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public UsernamePasswordAuthenticationToken toAuthentication() {
            return new UsernamePasswordAuthenticationToken(email, password);
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class MemberResponseDto {
        private String email;

        @Builder
        public MemberResponseDto(String email) {
            this.email = email;
        }

        public static MemberResponseDto of(Member saveMember) {
            return new MemberResponseDto(saveMember.getEmail());
        }
    }
}
