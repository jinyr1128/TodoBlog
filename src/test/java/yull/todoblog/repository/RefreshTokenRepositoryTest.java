package yull.todoblog.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import yull.todoblog.domain.RefreshToken;
import yull.todoblog.domain.User;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RefreshTokenRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    private RefreshToken refreshToken;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User("user@example.com", "password", "nickname");
        entityManager.persist(user);
        entityManager.flush();

        refreshToken = new RefreshToken(user.getId(), "refreshTokenString");
        entityManager.persist(refreshToken);
        entityManager.flush();
    }


    @Test
    @DisplayName("사용자 ID로 리프레시 토큰 찾기")
    void findByUserIdTest() {
        // given
        Long userId = user.getId();

        // when
        Optional<RefreshToken> foundToken = refreshTokenRepository.findByUserId(userId);

        // then
        assertThat(foundToken).isPresent();
        assertThat(foundToken.get().getRefreshToken()).isEqualTo("refreshTokenString");
    }

    @Test
    @DisplayName("리프레시 토큰 문자열로 리프레시 토큰 찾기")
    void findByRefreshTokenTest() {
        // given
        String tokenString = refreshToken.getRefreshToken();

        // when
        Optional<RefreshToken> foundToken = refreshTokenRepository.findByRefreshToken(tokenString);

        // then
        assertThat(foundToken).isPresent();
        assertThat(foundToken.get().getRefreshToken()).isEqualTo(tokenString);
    }
}
