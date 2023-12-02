package subproject.admin.jwt.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Service;
import subproject.admin.common.dto.ResultHandler;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class LogoutSuccessServiceImpl implements LogoutSuccessHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        response.setStatus(200);
        response.setContentType("application/json; charset=UTF-8");
        String result = objectMapper
                .writeValueAsString(ResultHandler.handle(HttpStatus.OK.value(), "로그아웃 되었습니다.", ""));
        response.getWriter().write(result);
        response.getWriter().flush();
        response.getWriter().close();
    }
}
