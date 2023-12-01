package yull.todoblog.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;
import yull.todoblog.domain.User;
import yull.todoblog.dto.AddUserRequest;
import yull.todoblog.repository.UserRepository;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UserService Test")
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should save new user")
    public void testSaveUser() {
        // given
        String email = "test@example.com";
        String password = "password";
        String encodedPassword = "encodedPassword";
        AddUserRequest request = new AddUserRequest();
        request.setEmail(email);
        request.setPassword(password);

        User user = new User(email, encodedPassword, "YourNickname");
        ReflectionTestUtils.setField(user, "id", 1L); // 'id' 필드에 1L 값을 설정
        when(userRepository.save(any(User.class))).thenReturn(user);

        when(bCryptPasswordEncoder.encode(password)).thenReturn(encodedPassword);
        when(userRepository.save(any(User.class))).thenReturn(user);
        // when
        Long userId = userService.save(request);

        // then
        assertNotNull(userId);
        verify(userRepository).save(any(User.class));
    }

    @Test
    @DisplayName("Should find user by ID")
    public void testFindById() {
        // given
        Long userId = 1L;
        User user = new User("test@example.com", "password", "username");
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // when
        User foundUser = userService.findById(userId);

        // then
        assertNotNull(foundUser);
    }

    @Test
    @DisplayName("Should find user by email")
    public void testFindByEmail() {
        // given
        String email = "test@example.com";
        String password = "password";
        String role = "ROLE_USER"; // 예시 role 값
        User user = new User(email, password, role); // User의 생성자에 따라 수정 필요

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        // when
        User foundUser = userService.findByEmail(email);

        // then
        assertNotNull(foundUser);
        assertEquals(email, foundUser.getEmail());
    }
}
