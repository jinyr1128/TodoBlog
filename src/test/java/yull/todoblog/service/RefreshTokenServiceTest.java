package yull.todoblog.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import yull.todoblog.common.security.domain.RefreshToken;
import yull.todoblog.common.security.repository.RefreshTokenRepository;
import yull.todoblog.common.security.service.RefreshTokenService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DisplayName("RefreshTokenService Test")
class RefreshTokenServiceTest {

    @Mock
    private RefreshTokenRepository refreshTokenRepository;

    @InjectMocks
    private RefreshTokenService refreshTokenService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("리프레시 토큰으로 토큰 찾기")
    void findByRefreshTokenTest() {
        // given
        Long userId = 1L; // 테스트에 사용할 userId, 실제 존재하는 사용자 ID여야 함
        String refreshTokenString = "refreshTokenString";
        RefreshToken refreshToken = new RefreshToken(userId, refreshTokenString);
        when(refreshTokenRepository.findByRefreshToken(refreshTokenString)).thenReturn(Optional.of(refreshToken));

        // when
        RefreshToken foundToken = refreshTokenService.findByRefreshToken(refreshTokenString);

        // then
        assertNotNull(foundToken);
        assertEquals(refreshTokenString, foundToken.getRefreshToken());
    }



    @Test
    @DisplayName("존재하지 않는 리프레시 토큰 찾기 시 예외 발생")
    void findByInvalidRefreshTokenTest() {
        // given
        String invalidToken = "invalidToken";
        when(refreshTokenRepository.findByRefreshToken(invalidToken)).thenReturn(Optional.empty());

        // when & then
        assertThrows(IllegalArgumentException.class, () -> {
            refreshTokenService.findByRefreshToken(invalidToken);
        });
    }
}
