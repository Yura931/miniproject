package sideproject.boardservice.jwt.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import sideproject.boardservice.common.dto.ResultHandler;
import sideproject.boardservice.global.exception.NotFoundTokenFromHeaderException;
import sideproject.boardservice.global.exception.enums.ErrorCode;


import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            filterChain.doFilter(request, response);
        } catch (NotFoundTokenFromHeaderException ex) {
            setErrorResponse(response, ErrorCode.NOT_FOUND_HEADER_TOKEN);
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException
                | UnsupportedJwtException | IllegalArgumentException ex) {
            setErrorResponse(response, ErrorCode.INVALID_TOKEN);
        } catch (ExpiredJwtException ex) {
            setErrorResponse(response, ErrorCode.EXPIRED_JWT_TOKEN);
        }

    }

    public void setErrorResponse(HttpServletResponse res, ErrorCode ec) throws IOException {
        res.setStatus(ec.getStatus().value());
        res.setContentType("application/json; charset=UTF-8");
        String result = objectMapper.writeValueAsString(ResultHandler.errorHandle(ec));
        res.getWriter().write(result);
        res.getWriter().flush();
        res.getWriter().close();
    }
}
