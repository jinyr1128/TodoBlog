package yull.todoblog.common.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
// CreateAccessTokenResponse: 액세스 토큰 생성 응답을 위한 DTO
public class CreateAccessTokenResponse {
    private String accessToken; // 새로 생성된 액세스 토큰
}