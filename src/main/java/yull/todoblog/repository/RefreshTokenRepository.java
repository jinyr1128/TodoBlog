package yull.todoblog.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import yull.todoblog.domain.RefreshToken;

import java.util.Optional;

// RefreshTokenRepository: 리프레시 토큰(RefreshToken) 엔티티에 대한 데이터 접근을 처리하는 JPA 리포지토리
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    // 특정 사용자(User)의 리프레시 토큰을 찾기 위한 메서드
    Optional<RefreshToken> findByUserId(Long userId);

    // 리프레시 토큰 문자열을 통해 리프레시 토큰을 찾기 위한 메서드
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
