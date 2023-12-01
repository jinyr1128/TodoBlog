package yull.todoblog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// LoginRequest: 로그인 요청을 위한 DTO
public class LoginRequest {
    private String username; // 사용자 이름
    private String password; // 비밀번호
}
