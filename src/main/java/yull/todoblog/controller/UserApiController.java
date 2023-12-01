package yull.todoblog.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import yull.todoblog.dto.AddUserRequest;
import yull.todoblog.service.UserService;

@RequiredArgsConstructor
@Controller
// UserApiController: 사용자 관련 API 요청을 처리하는 컨트롤러
public class UserApiController {

    private final UserService userService;

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

}