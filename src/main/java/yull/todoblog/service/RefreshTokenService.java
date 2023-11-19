package yull.todoblog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yull.todoblog.domain.RefreshToken;
import yull.todoblog.repository.RefreshTokenRepository;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected token"));
    }
}