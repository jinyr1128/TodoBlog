package yull.todoblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yull.todoblog.domain.User;

import java.util.Optional;

// UserRepository: 사용자(User) 엔티티에 대한 데이터 접근을 처리하는 JPA 리포지토리
public interface UserRepository extends JpaRepository<User, Long> {
    // 이메일을 기반으로 사용자를 찾는 메서드
    Optional<User> findByEmail(String email);
}
