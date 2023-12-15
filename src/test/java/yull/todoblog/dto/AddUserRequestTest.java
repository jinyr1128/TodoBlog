package yull.todoblog.dto;

import org.junit.jupiter.api.Test;
import yull.todoblog.user.dto.AddUserRequest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddUserRequestTest {

    @Test
    public void testIsValidUsername() {
        // given: 유효한 사용자 이름
        AddUserRequest requestWithValidUsername = new AddUserRequest();
        requestWithValidUsername.setUsername("validuser");

        // when: 사용자 이름 유효성 검증
        boolean isValid = requestWithValidUsername.isValidUsername();

        // then: 사용자 이름이 유효한지 확인
        assertTrue(isValid);

        // given: 유효하지 않은 사용자 이름
        AddUserRequest requestWithInvalidUsername = new AddUserRequest();
        requestWithInvalidUsername.setUsername("invalid user");

        // when: 사용자 이름 유효성 검증
        isValid = requestWithInvalidUsername.isValidUsername();

        // then: 사용자 이름이 유효하지 않은지 확인
        assertFalse(isValid);
    }

    @Test
    public void testIsValidPassword() {
        // given: 유효한 비밀번호
        AddUserRequest requestWithValidPassword = new AddUserRequest();
        requestWithValidPassword.setPassword("ValidPass123");

        // when: 비밀번호 유효성 검증
        boolean isValid = requestWithValidPassword.isValidPassword();

        // then: 비밀번호가 유효한지 확인
        assertTrue(isValid);

        // given: 유효하지 않은 비밀번호
        AddUserRequest requestWithInvalidPassword = new AddUserRequest();
        requestWithInvalidPassword.setPassword("short");

        // when: 비밀번호 유효성 검증
        isValid = requestWithInvalidPassword.isValidPassword();

        // then: 비밀번호가 유효하지 않은지 확인
        assertFalse(isValid);
    }
}