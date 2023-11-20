package yull.todoblog.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import yull.todoblog.domain.User;
import yull.todoblog.dto.AddUserRequest;
import yull.todoblog.repository.UserRepository;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    // 사용자 저장
    public Long save(AddUserRequest dto) {
        validateEmail(dto.getEmail());
        validatePassword(dto.getPassword());
        checkEmailExists(dto.getEmail());

        return userRepository.save(User.builder()
                .email(dto.getEmail())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build()).getId();
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

    // ID로 사용자 찾기
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }
}