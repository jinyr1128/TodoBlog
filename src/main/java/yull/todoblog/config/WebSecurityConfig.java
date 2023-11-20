package yull.todoblog.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
import yull.todoblog.service.UserDetailService;


// WebSecurityConfig 클래스: Spring Security 설정을 정의하는 클래스
@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {

    private final UserDetailService userService;// 사용자 정보를 조회하는 서비스

    // Spring Security의 기본 설정을 정의하는 메서드
    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring()
                .requestMatchers(toH2Console())
                .requestMatchers("/static/**");
    }

    // 보안 필터 체인을 구성하는 메서드
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests()
                .requestMatchers("/login", "/signup", "/user").permitAll() // 특정 경로에 대한 접근 허용
                .anyRequest().authenticated() // 그 외 요청은 인증 필요
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/articles") // 로그인 성공 후 리디렉션 경로
                .and()
                .logout()
                .logoutSuccessUrl("/login") // 로그아웃 성공 후 리디렉션 경로
                .invalidateHttpSession(true) // 세션 무효화
                .and()
                .csrf().disable() // CSRF 보안 비활성화
                .build();
    }
    // AuthenticationManager 빈을 구성하는 메서드
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailService userDetailService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userService) // 사용자 정보 서비스 설정
                .passwordEncoder(bCryptPasswordEncoder) // 비밀번호 인코더 설정
                .and()
                .build();
    }
    // BCryptPasswordEncoder 빈을 구성하는 메서드
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}