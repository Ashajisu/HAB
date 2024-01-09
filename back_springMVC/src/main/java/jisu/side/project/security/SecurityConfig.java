//package jisu.side.project.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.SecurityBuilder;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import jakarta.servlet.DispatcherType;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import static org.springframework.security.config.Customizer.withDefaults;
//
///**
// * 스프링 부트 2.7.0 이상의 버전을 사용하면 스프링 시큐리티 5.7.0 혹은 이상의 버전과 의존성이 있습니다.
// * 그렇다면 WebSecurityConfigurerAdapter가 deprecated되며
// * 스프링 부트 3 이상의 버전과 의존관계인 스프링 시큐리티 6 이상에서는 클래스가 제거되었음
// *
// * 참고 https://covenant.tistory.com/277
// * */
//@Configuration
//@EnableMethodSecurity
//public class SecurityConfig {
//
//    @Bean
//    AuthenticationManager authenticationManager(
//            AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable().cors();
//        http.authorizeHttpRequests(request -> request
//                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
//                        .requestMatchers("/api/auth/**").permitAll()
//                        .requestMatchers("/login/**").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .formLogin(login -> login
////                        .loginPage("/view/login")
////                        .loginProcessingUrl("/login/process")
//                        .usernameParameter("userid")
//                        .passwordParameter("pw")
//                        .defaultSuccessUrl("/", true)
//                        .permitAll()
//                )
//                .logout(withDefaults());
//
//        return http.build();
//    }
//
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("userid")
//                .password("pw")
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }
//
////    @Bean
////    public BCryptPasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    } // User service에서 사용해야함
//
//    /** 이전버전
//     *     @Bean
//     *     public PasswordEncoder passwordEncoder() {
//     *         return new BCryptPasswordEncoder();
//     *     }
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("userid")
//                .password(passwordEncoder().encode("pw"))
//                .roles("USER");
//    }
//
//     변경후
//    AuthenticationManager 생성시 UserSecurityService와 PasswordEncoder가 자동으로 설정
//    */
//}