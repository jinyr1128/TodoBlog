package yull.todoblog.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
// AddUserRequest: 새로운 사용자 추가 요청을 위한 DTO
public class AddUserRequest {
    private String username; // 사용자 이름
    private String email; // 이메일
    private String password; // 비밀번호

    // 유효한 사용자 이름인지 검증
    public boolean isValidUsername() {
        return username != null && username.matches("^[a-z0-9]{4,10}$");
    }

    // 유효한 비밀번호인지 검증
    public boolean isValidPassword() {
        return password != null && password.matches("^[a-zA-Z0-9]{8,15}$");
    }
}



