package com.github.hanjungho.oauth2jwttest.oauth2;

import com.github.hanjungho.oauth2jwttest.domain.dto.CustomOAuth2User;
import com.github.hanjungho.oauth2jwttest.jwt.JWTUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JWTUtil jwtUtil;

    @Value("${front.url}")
    private String frontUrl;

    public CustomSuccessHandler(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("OAuth2 로그인 성공! 인증 정보: " + authentication);

        //OAuth2User
        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();

        String username = customUserDetails.getUsername();
        System.out.println("사용자 이름: " + username);

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();
        System.out.println("사용자 권한: " + role);

        String token = jwtUtil.createJwt(username, role, 60*60*60*60L);
        System.out.println("생성된 JWT 토큰: " + token.substring(0, Math.min(token.length(), 10)) + "...");

        Cookie cookie = createCookie("Authorization", token);
        System.out.println("쿠키 생성: " + cookie.getName() + ", maxAge: " + cookie.getMaxAge() + ", httpOnly: " + cookie.isHttpOnly());

        response.addCookie(cookie);
        System.out.println("리다이렉트 주소: " + frontUrl);
        response.sendRedirect(frontUrl);
    }

    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(60*60*60*60);
        //cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }
}