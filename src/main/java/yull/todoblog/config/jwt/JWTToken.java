package yull.todoblog.config.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JWTToken { // JWTToken 클래스: JWT 토큰을 관리하는 클래스
    private String token;// JWT 토큰 문자열
}
