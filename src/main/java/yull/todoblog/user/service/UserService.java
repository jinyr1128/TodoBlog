package yull.todoblog.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import yull.todoblog.user.domain.User;
import yull.todoblog.user.dto.AddUserRequest;
import yull.todoblog.user.repository.UserRepository;


@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public Long save(AddUserRequest dto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return userRepository.save(User.builder()
                .email(dto.getEmail())
                .password(encoder.encode(dto.getPassword()))
                .build()).getId();
    }
    // ID로 사용자 찾기
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }


    // 이메일 유효성 검사
    private void validateEmail(String email) {
        if (email == null || !email.matches("^[a-z0-9]{4,10}$")) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }

    // 패스워드 유효성 검사
    private void validatePassword(String password) {
        if (password == null || !password.matches("^[a-zA-Z0-9]{8,15}$")) {
            throw new IllegalArgumentException("Invalid password format");
        }
    }

    // 이메일 중복 검사
    private void checkEmailExists(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
    }
}