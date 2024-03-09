package sideproject.authservice.common.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;


import java.util.Optional;
import java.util.stream.Stream;

import static sideproject.authservice.jwt.properties.JwtProperties.*;

@Component
public class CookieUtil {

    public void addCookie(HttpServletResponse response, String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(REFRESH_TOKEN_EXPIRE_TIME.intValue());
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public Optional<Cookie> getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        return Stream.of(cookies)
                .filter((cookie -> cookie.getName().equals(name)))
                .findFirst();
    }

    public void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie[] cookies = request.getCookies();

        Stream.of(cookies)
                .filter((cookie -> cookie.getName().equals(name)))
                .findFirst()
                .ifPresent((cookie -> {
                    cookie.setValue("");
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }));
    }
}
