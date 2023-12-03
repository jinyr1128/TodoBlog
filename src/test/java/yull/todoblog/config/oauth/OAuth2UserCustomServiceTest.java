package yull.todoblog.config.oauth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import yull.todoblog.domain.User;
import yull.todoblog.repository.UserRepository;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class OAuth2UserCustomServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private OAuth2UserCustomService oAuth2UserCustomService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("OAuth2 사용자 로드 및 저장 또는 업데이트")
    void testLoadUserAndSaveOrUpdate() {
        // given
        ClientRegistration clientRegistration = ClientRegistration.withRegistrationId("registration-id")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("http://localhost:8080/login/oauth2/code/registration-id")
                .clientId("client-id")
                .clientSecret("client-secret")
                .authorizationUri("http://localhost:8080/oauth/authorize")
                .tokenUri("http://localhost:8080/oauth/token")
                .userInfoUri("http://localhost:8080/userinfo")
                .userNameAttributeName("id")
                .build();

        OAuth2UserRequest userRequest = new OAuth2UserRequest(clientRegistration, new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER, "token", Instant.now(), Instant.now().plusSeconds(60)));

        // when
        OAuth2User result = oAuth2UserCustomService.loadUser(userRequest);

        // then
        assertNotNull(result);
        assertEquals("test@example.com", result.<String>getAttribute("email"));
        assertEquals("Test Name", result.<String>getAttribute("name"));
        verify(userRepository, times(1)).save(any(User.class));
    }
}
