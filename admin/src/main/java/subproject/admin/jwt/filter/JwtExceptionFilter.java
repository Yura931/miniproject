package subproject.admin.jwt.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import subproject.admin.common.dto.Result;
import subproject.admin.common.dto.ResultHandler;
import subproject.admin.common.enums.ErrorCode;
import subproject.admin.global.exception.ExpiredAccessTokenException;
import subproject.admin.global.exception.ExpiredRefreshTokenException;
import subproject.admin.global.exception.LogoutTokenRequestException;
import subproject.admin.global.exception.NotFoundTokenFromHeaderException;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (ExpiredAccessTokenException ex) {
            setErrorResponse(response, ErrorCode.EXPIRED_ACCESS_TOKEN);
        } catch (ExpiredRefreshTokenException ex) {
            setErrorResponse(response, ErrorCode.EXPIRED_REFRESH_TOKEN);
        } catch (ExpiredJwtException ex) {
            setErrorResponse(response, ErrorCode.EXPIRED_JWT_TOKEN);
        } catch (LogoutTokenRequestException ex) {
            setErrorResponse(response, ErrorCode.LOGOUT_TOKEN);
        } catch (NotFoundTokenFromHeaderException ex) {
            setErrorResponse(response, ErrorCode.HEADER_TOKEN_NOT_FOUND);
        } catch (JwtException ex) {
            setErrorResponse(response, ErrorCode.INVALID_TOKEN);
        }
    }

    public void setErrorResponse(HttpServletResponse res, ErrorCode ec) throws IOException {
        res.setStatus(ec.getStatus());
        res.setContentType("application/json; charset=UTF-8");
        String result = objectMapper.
                writeValueAsString(ResultHandler.errorHandle(ec));
        res.getWriter().write(result);
        res.getWriter().flush();
        res.getWriter().close();
    }
}
