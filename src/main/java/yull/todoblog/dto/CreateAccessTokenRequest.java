package yull.todoblog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// CreateAccessTokenRequest: 액세스 토큰 생성 요청을 위한 DTO
public class CreateAccessTokenRequest {
    private String refreshToken; // 리프레시 토큰
}