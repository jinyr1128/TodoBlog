package yull.todoblog.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import yull.todoblog.user.domain.User;
import yull.todoblog.user.repository.UserRepository;
import yull.todoblog.user.service.UserDetailService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DisplayName("UserDetailService Test")
public class UserDetailServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailService userDetailService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("사용자 이름(이메일)으로 사용자를 로드")
    public void testLoadUserByUsername() {
        // given
        String email = "test@example.com";
        User mockUser = new User(email, "password", "nickname"); // User 객체 초기화
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(mockUser));

        // when
        User foundUser = userDetailService.loadUserByUsername(email);

        // then
        assertNotNull(foundUser);
        assertEquals(email, foundUser.getUsername());
    }

    @Test
    @DisplayName("사용자를 찾을 수 없을 때 예외")
    public void testLoadUserByUsernameNotFound() {
        // given
        String email = "nonexistent@example.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // when
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userDetailService.loadUserByUsername(email);
        });

        // then
        assertEquals(email, exception.getMessage());
    }
}
