package yull.todoblog.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateAccessTokenRequestTest {

    @Test
    public void testRefreshTokenHandling() {
        // given: 리프레시 토큰 값 설정
        String expectedRefreshToken = "sampleRefreshToken";
        CreateAccessTokenRequest request = new CreateAccessTokenRequest();
        request.setRefreshToken(expectedRefreshToken);

        // when: 리프레시 토큰 값 검색
        String actualRefreshToken = request.getRefreshToken();

        // then: 설정된 리프레시 토큰 값이 정상적으로 검색되는지 검증
        assertEquals(expectedRefreshToken, actualRefreshToken);
    }
}
