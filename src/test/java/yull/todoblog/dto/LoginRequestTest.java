package yull.todoblog.dto;

import org.junit.jupiter.api.Test;
import yull.todoblog.user.dto.LoginRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginRequestTest {

    @Test
    public void testUsernameAndPasswordHandling() {
        // given: 사용자 이름과 비밀번호 설정
        String expectedUsername = "testUser";
        String expectedPassword = "testPassword";
        LoginRequest request = new LoginRequest();
        request.setUsername(expectedUsername);
        request.setPassword(expectedPassword);

        // when: 설정된 사용자 이름과 비밀번호 검색
        String actualUsername = request.getUsername();
        String actualPassword = request.getPassword();

        // then: 설정된 사용자 이름과 비밀번호가 정상적으로 검색되는지 검증
        assertEquals(expectedUsername, actualUsername);
        assertEquals(expectedPassword, actualPassword);
    }
}
