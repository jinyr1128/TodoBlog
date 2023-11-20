package yull.todoblog.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import yull.todoblog.domain.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import yull.todoblog.config.jwt.JWTToken;
import yull.todoblog.config.jwt.TokenProvider;
import yull.todoblog.dto.AddUserRequest;
import yull.todoblog.dto.LoginRequest;
import yull.todoblog.service.UserService;

import java.time.Duration;

@RequiredArgsConstructor
@Controller
// UserApiController: 사용자 관련 API 요청을 처리하는 컨트롤러
public class UserApiController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager; // 인증 관리자
    private final TokenProvider tokenProvider; // 토큰 제공자

    // 사용자 회원가입 처리
    @PostMapping("/user")
    public String signup(AddUserRequest request) {
        userService.save(request);
        return "redirect:/login"; // 로그인 페이지로 리디렉션
    }

    // 사용자 로그아웃 처리
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login"; // 로그인 페이지로 리디렉션
    }

    // 사용자 로그인 처리 및 JWT 토큰 발급
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwt = tokenProvider.generateToken(userDetails, Duration.ofHours(2));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwt);
        return new ResponseEntity<>(new JWTToken(jwt), headers, HttpStatus.OK);
    }
}
