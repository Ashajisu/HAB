package jisu.side.project.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jisu.side.project.dto.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Order(0)
@RequiredArgsConstructor
@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = parseBearerToken(request); //토큰추출
        log.info("JwtAuthenticationFilter -get token from request headers : {}", token);
        if (token != null && tokenProvider.validateJwtToken(token)) { //토큰 유효성 검사
        SecurityUser securityUser = parseUserSpecification(token); //토큰에서 유저정보 추출
        log.info("JwtAuthenticationFilter -user : {}", securityUser);
        AbstractAuthenticationToken authenticated = UsernamePasswordAuthenticationToken.authenticated(securityUser, null, securityUser.getAuthorities()); //패스워드 확인 후 권한생성
        authenticated.setDetails(new WebAuthenticationDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticated);
        }

        filterChain.doFilter(request, response);
    }

    private String parseBearerToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION))
                .filter(token -> token.substring(0, 7).equalsIgnoreCase("Bearer "))
                .map(token -> token.substring(7))
                .orElse(null);
    }

    private SecurityUser parseUserSpecification(String token) {
        String[] split = Optional.ofNullable(token)
                .filter(subject -> subject.length() >= 10)
                .map(tokenProvider::validateTokenAndGetSubject)
                .orElse("anonymous:anonymous")
                .split(":");

        return new SecurityUser(split[0], "", Role.valueOf(split[1]));
    }
}
