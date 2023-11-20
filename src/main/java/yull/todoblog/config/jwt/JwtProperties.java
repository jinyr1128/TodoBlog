package yull.todoblog.config.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties("jwt")// JwtProperties 클래스: JWT 설정을 위한 속성을 관리하는 클래스
public class JwtProperties {
    private String issuer;    // 발행자
    private String secretKey; // 비밀키
}
