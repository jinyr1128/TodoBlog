package yull.todoblog.service;

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


    public Long save(AddUserRequest dto) {
        validateEmail(dto.getEmail());
        validatePassword(dto.getPassword());
        checkEmailExists(dto.getEmail());

        return userRepository.save(User.builder()
                .email(dto.getEmail())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build()).getId();
    }

    private void validateEmail(String email) {
        if (email == null || !email.matches("^[a-z0-9]{4,10}$")) {
            throw new IllegalArgumentException("email format");
        }
    }

    private void validatePassword(String password) {
        if (password == null || !password.matches("^[a-zA-Z0-9]{8,15}$")) {
            throw new IllegalArgumentException("password format");
        }
    }

    private void checkEmailExists(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email already");
        }
    }
}