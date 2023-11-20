package subproject.admin.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.Valid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import subproject.admin.jwt.dto.TokenDto;
import subproject.admin.jwt.properties.JwtProperties;
import subproject.admin.user.dto.MemberDto;
import subproject.admin.user.entity.Member;
import subproject.admin.user.entity.MemberRole;
import subproject.admin.user.repository.MemberRepository;

import javax.crypto.SecretKey;
import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static subproject.admin.jwt.properties.JwtProperties.*;

@SpringBootTest
class TokenProviderTest {

    @Autowired
    MemberRepository memberRepository;

    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30;    // 30분
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;  // 7일
    private TokenDto tokenDto;
    private TokenDto invalidJwtToken;
    private TokenDto notHavePrefixJwtToken;

    public static class AccessTokenClaimDto {
        public UUID memberId;
        public List<MemberRole> roles;

        public void setMemberId(UUID memberId) {
            this.memberId = memberId;
        }

        public void setRoles(List<MemberRole> roles) {
            this.roles = roles;
        }

        public UUID getMemberId() {
            return memberId;
        }

        public List<MemberRole> getRoles() {
            return roles;
        }

        public AccessTokenClaimDto(UUID memberId, List<MemberRole> roles) {
            this.memberId = memberId;
            this.roles = roles;
        }
    }

    @BeforeEach
    public void 토큰세팅() throws Exception {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        Key key = Keys.hmacShaKeyFor(keyBytes);
        Member member = Member.joinNewAdminMember("test@email.com", "test1234");
        MemberRole memberRole = MemberRole.generateNewMemberByRoleAdmin(member);
        member.saveMemberRole(Collections.singletonList(memberRole));

        Date accessTokenExpiresIn = new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRE_TIME);
        String accessToken = Jwts.builder()
                .setSubject(member.getEmail())
                .claim(AUTHORITIES_KEY, new AccessTokenClaimDto(member.getId(), member.getRoles()))
                .setExpiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        String refreshToken = Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRE_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        tokenDto = TokenDto.createToken(TOKEN_PREFIX, accessToken, accessTokenExpiresIn.getTime(), refreshToken);

        accessToken = Jwts.builder()
                .setSubject(member.getEmail())
                .claim(AUTHORITIES_KEY, member.getId())
                .setExpiration(new Date(System.currentTimeMillis() - 1000000))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        refreshToken = Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() - 1000000))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        invalidJwtToken = TokenDto.createToken(TOKEN_PREFIX, accessToken, accessTokenExpiresIn.getTime(), refreshToken);
    }

    @Test
    public void accessToken유효시간체크() throws Exception {
        // tokenDto 유효한 토큰
        assertTrue(tokenDto.isAccessTokenValid());
        // invalidJwtToken 유효시간 만료된 토큰
        assertThrows(ExpiredJwtException.class, () -> invalidJwtToken.isAccessTokenValid());
    }

    @Test
    public void refreshToken유효시간체크() throws Exception {
        assertTrue(tokenDto.isRefreshTokenValid());
        assertThrows(ExpiredJwtException.class, () -> invalidJwtToken.isRefreshTokenValid());
    }

}