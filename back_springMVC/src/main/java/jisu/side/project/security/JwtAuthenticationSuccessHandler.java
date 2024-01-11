package jisu.side.project.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
 /**해당 클래스는 @Component이 붙어있어 이미 Spring의 컴포넌트 스캔에 의해 빈으로 등록되어 있기 때문에
  *  SecurityConfig에 어노테이션 @Bean을 사용하여 추가적으로 빈을 등록할 필요가 없습니다.
  *  필요한 경우 @Autowired 또는 @RequiredArgsConstructor를 사용하여 의존성 주입을 받는 방법을 사용하면 됩니다.
  *  이때 Spring은 해당 클래스의 인스턴스를 싱글톤으로 관리하게 됩니다.
  * **/
    private final TokenProvider tokenProvider;
    //Principal=SecurityUser(username=testtoken, password=$2a$10$1X1n7Y13mwtagUXAo/8obuC0nj3skAHvNW7eB7L4MqrHnJ/YfgOzu, role=USER)

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException{
        log.info("AuthenticationSuccessHandler");
//        SecurityUser userSpecification = (SecurityUser)authentication.getPrincipal();
        User userSpecification = (User)authentication.getPrincipal();
        String subject = userSpecification.getUsername() + ":" + userSpecification.getAuthorities().toString();
        log.info("onAuthenticationSuccess - subject : {}", subject);
        // 로그인 성공 시 JWT 토큰 생성
        String token = tokenProvider.createToken(subject);

        // JWT를 응답 헤더에 추가
        response.addHeader("Authorization", "Bearer " + token);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        //response.getWriter().write("{ \"token\": \"" + token + "\" }"); //본문에 글 작성
        //response.getWriter().flush();// 본문을 클라이언트로 보낸다는 의미이며, 그 이후에는 헤더나 상태 코드를 변경하는 작업이 어렵습니다.


        // JWT를 쿠키에 추가
        Cookie cookie = new Cookie("jwtToken", token);


        // 서버에서 리다이렉션 지정하면 클라이언트는 이전 response는 전달받지 못하고 "/"로 이동됨.
        response.sendRedirect(request.getContextPath() + "/testJWT");
    }
}

