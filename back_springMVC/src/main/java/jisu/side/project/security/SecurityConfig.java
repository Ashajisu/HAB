package jisu.side.project.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import jakarta.servlet.DispatcherType;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * 스프링 부트 2.7.0 이상의 버전을 사용하면 스프링 시큐리티 5.7.0 혹은 이상의 버전과 의존성이 있습니다.
 * 그렇다면 WebSecurityConfigurerAdapter가 deprecated되며
 * 스프링 부트 3 이상의 버전과 의존관계인 스프링 시큐리티 6 이상에서는 클래스가 제거되었음
 *
 * 참고 https://covenant.tistory.com/277
 * */
@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenProvider tokenProvider;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final SecurityUserDetailsService securityUserDetailsService;

    private final JwtAuthenticationSuccessHandler jwtAuthenticationSuccessHandler; //스프링 컨테이너에서 관리되는 싱글톤 객체 주입
    //상태를 가질 경우 다수의 요청이 동시에 들어올 때 충돌이 발생할 수 있습니다.
    //일반적으로 상태를 가지지 않고 오로지 요청에 대한 처리만을 하는 핸들러라면 싱글톤으로 등록하는 것이 일반적

    private final String[] allowedUrls = {"/api/auth/sign/**"};

    @Bean //호출 시마다 새로운 객체 인스턴스를 생성
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    } //User service에서 사용해야함

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(securityUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors();
        http.authorizeHttpRequests(request -> request
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
//                        .requestMatchers(allowedUrls).permitAll()
//                        .requestMatchers("/login/**").permitAll()
                        .anyRequest().authenticated()
                )
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //세션 미사용
                .formLogin(login -> login
//                        .loginPage("/login") //로그인 폼을 제출 할 페이지
//                        .loginProcessingUrl("/api/auth/sign/login") //폼 제출시 해당 URL로 POST 요청이 가게 되고 컨트롤러에서 직접 로그인을 처리
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .successHandler(jwtAuthenticationSuccessHandler)  //로그인 성공시 응답 헤더에 토큰 추가
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .logout(withDefaults())
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return securityUserDetailsService;
//    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("userid")
//                .password(passwordEncoder().encode("pw"))
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }


    /** 이전버전
     *     @Bean
     *     public PasswordEncoder passwordEncoder() {
     *         return new BCryptPasswordEncoder();
     *     }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("userid")
                .password(passwordEncoder().encode("pw"))
                .roles("USER");
    }

     변경후
    AuthenticationManager 생성시 UserSecurityService와 PasswordEncoder가 자동으로 설정
    */
}