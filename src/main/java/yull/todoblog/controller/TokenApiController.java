package yull.todoblog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import yull.todoblog.dto.CreateAccessTokenRequest;
import yull.todoblog.dto.CreateAccessTokenResponse;
import yull.todoblog.service.TokenService;

@RequiredArgsConstructor
@RestController
// TokenApiController: 토큰 관련 API 요청을 처리하는 컨트롤러
public class TokenApiController {

    private final TokenService tokenService; // 토큰 서비스

    // 새로운 액세스 토큰 생성 API
    @PostMapping("/api/token")
    public ResponseEntity<CreateAccessTokenResponse> createNewAccessToken(@RequestBody CreateAccessTokenRequest request) {
        String newAccessToken = tokenService.createNewAccessToken(request.getRefreshToken());
        return ResponseEntity.status(HttpStatus.CREATED).body(new CreateAccessTokenResponse(newAccessToken));
    }
}
