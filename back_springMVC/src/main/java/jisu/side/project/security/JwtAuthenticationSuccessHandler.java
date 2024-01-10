package jisu.side.project.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final TokenProvider tokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String userSpecification = authentication.getPrincipal().toString();
        log.info("onAuthenticationSuccess - userSpecification : {}", userSpecification);
        // 로그인 성공 시 JWT 토큰 생성
        String token = tokenProvider.createToken(userSpecification);

        // JWT를 응답 헤더에 추가
        response.addHeader("Authorization", "Bearer " + token);

        // 리다이렉션은 원래의 defaultSuccessUrl("/")로 수행
        response.sendRedirect(request.getContextPath() + "/");
    }
}

