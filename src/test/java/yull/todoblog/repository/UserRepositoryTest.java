package yull.todoblog.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import yull.todoblog.domain.User;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        // 초기 사용자 데이터 설정
        User user = new User("user@example.com", "password", "nickname");
        entityManager.persist(user);
        entityManager.flush();
    }

    @Test
    @DisplayName("이메일로 사용자 찾기")
    void findByEmailTest() {
        // given
        String email = "user@example.com";

        // when
        Optional<User> foundUser = userRepository.findByEmail(email);

        // then
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getEmail()).isEqualTo(email);
    }
}
