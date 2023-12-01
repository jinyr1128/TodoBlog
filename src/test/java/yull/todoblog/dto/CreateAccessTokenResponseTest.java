package yull.todoblog.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateAccessTokenResponseTest {

    @Test
    public void testAccessTokenHandling() {
        // given: 액세스 토큰 값 설정하여 객체 생성
        String expectedAccessToken = "sampleAccessToken";
        CreateAccessTokenResponse response = new CreateAccessTokenResponse(expectedAccessToken);

        // when: 생성된 객체에서 액세스 토큰 값 검색
        String actualAccessToken = response.getAccessToken();

        // then: 객체에 설정된 액세스 토큰 값이 정상적으로 검색되는지 검증
        assertEquals(expectedAccessToken, actualAccessToken);
    }
}
