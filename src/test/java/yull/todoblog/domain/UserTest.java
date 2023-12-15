package yull.todoblog.domain;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import yull.todoblog.user.domain.User;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserTest {

    @Autowired
    private EntityManager entityManager;

    @Test
    @DisplayName("User 객체 생성 및 필드 검증 테스트")
    public void testUserCreationAndFields() {
        // given: User 객체 생성
        String email = "test@example.com";
        String password = "password";
        String nickname = "TestUser";
        User user = User.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .build();

        // when: User 객체를 데이터베이스에 저장
        entityManager.persist(user);
        entityManager.flush();

        // then: User 객체의 필드들이 정상적으로 설정되었는지 검증
        assertNotNull(user.getId());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertEquals(nickname, user.getNickname());
    }

    @Test
    @DisplayName("UserDetails 인터페이스 구현 검증 테스트")
    public void testUserDetailsImplementation() {
        // given: UserDetails로서의 User 객체 생성
        User user = User.builder()
                .email("user@example.com")
                .password("password")
                .nickname("Nickname")
                .build();

        // when: UserDetails 인터페이스 메서드 호출
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        // then: UserDetails 인터페이스 구현이 정상적으로 작동하는지 검증
        assertNotNull(authorities);
        assertFalse(authorities.isEmpty());
        assertEquals("user", authorities.iterator().next().getAuthority());
        assertTrue(user.isAccountNonExpired());
        assertTrue(user.isAccountNonLocked());
        assertTrue(user.isCredentialsNonExpired());
        assertTrue(user.isEnabled());
    }
}