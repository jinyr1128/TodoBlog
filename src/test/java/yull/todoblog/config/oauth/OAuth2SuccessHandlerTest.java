package yull.todoblog.config.oauth;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import yull.todoblog.config.jwt.TokenProvider;
import yull.todoblog.domain.RefreshToken;
import yull.todoblog.domain.User;
import yull.todoblog.repository.RefreshTokenRepository;
import yull.todoblog.service.UserService;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.Map;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(MockitoExtension.class)
public class OAuth2SuccessHandlerTest {

    @Mock
    private TokenProvider tokenProvider;

    @Mock
    private RefreshTokenRepository refreshTokenRepository;

    @Mock
    private OAuth2AuthorizationRequestBasedOnCookieRepository authorizationRequestRepository;

    @Mock
    private UserService userService;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private OAuth2SuccessHandler successHandler;

    @Test
    public void onAuthenticationSuccessTest() throws IOException {
        // given
        OAuth2User oAuth2User = new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                Map.of("email", "test@example.com", "id", 1L), // 여기에 사용자 ID 추가
                "email");
        when(authentication.getPrincipal()).thenReturn(oAuth2User);

        User user = User.builder()
                .email("test@example.com")
                .password("testPassword")
                .nickname("TestUser")
                .build();
        when(userService.findByEmail("test@example.com")).thenReturn(user);

        when(tokenProvider.generateToken(any(User.class), any(Duration.class)))
                .thenReturn("someAccessToken", "someRefreshToken");

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        // when
        successHandler.onAuthenticationSuccess(request, response, authentication);

        // then
        verify(tokenProvider, times(2)).generateToken(any(User.class), any(Duration.class));
        verify(refreshTokenRepository).save(any(RefreshToken.class));

        String expectedRedirectUrl = "/articles?token=someRefreshToken";
        String actualRedirectUrl = response.getRedirectedUrl();
        assertEquals(expectedRedirectUrl, actualRedirectUrl, "리디렉트 URL이 일치하지 않습니다.");
    }
}
