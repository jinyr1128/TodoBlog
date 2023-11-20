package yull.todoblog.domain;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
// RefreshToken: 리프레시 토큰에 관한 엔티티 클래스
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id; // 고유 ID

    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId; // 사용자 ID

    @Column(name = "refresh_token", nullable = false)
    private String refreshToken; // 리프레시 토큰

    // 생성자
    public RefreshToken(Long userId, String refreshToken) {
        this.userId = userId;
        this.refreshToken = refreshToken;
    }

    // 리프레시 토큰 갱신 메서드
    public RefreshToken update(String newRefreshToken) {
        this.refreshToken = newRefreshToken;
        return this;
    }
}