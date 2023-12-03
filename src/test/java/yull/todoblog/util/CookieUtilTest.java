package yull.todoblog.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.util.SerializationUtils;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CookieUtilTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("쿠키 추가")
    void addCookieTest() {
        // given
        String name = "testCookie";
        String value = "testValue";
        int maxAge = 60;

        // when
        CookieUtil.addCookie(response, name, value, maxAge);

        // then
        verify(response).addCookie(argThat(cookie ->
                cookie.getName().equals(name) &&
                        cookie.getValue().equals(value) &&
                        cookie.getMaxAge() == maxAge &&
                        "/".equals(cookie.getPath())
        ));
    }

    @Test
    @DisplayName("쿠키 삭제")
    void deleteCookieTest() {
        // given
        String name = "testCookie";
        Cookie[] cookies = new Cookie[]{new Cookie(name, "value")};
        when(request.getCookies()).thenReturn(cookies);

        // when
        CookieUtil.deleteCookie(request, response, name);

        // then
        verify(response).addCookie(argThat(cookie ->
                cookie.getName().equals(name) &&
                        cookie.getValue().isEmpty() &&
                        cookie.getMaxAge() == 0 &&
                        "/".equals(cookie.getPath())
        ));
    }

    @Test
    @DisplayName("객체 직렬화")
    void serializeTest() {
        // given
        String testObject = "Test String";

        // when
        String serialized = CookieUtil.serialize(testObject);

        // then
        assertEquals(
                Base64.getUrlEncoder().encodeToString(SerializationUtils.serialize(testObject)),
                serialized
        );
    }

    @Test
    @DisplayName("객체 역직렬화")
    void deserializeTest() {
        // given
        String testObject = "Test String";
        String serialized = Base64.getUrlEncoder().encodeToString(SerializationUtils.serialize(testObject));
        Cookie cookie = new Cookie("test", serialized);

        // when
        String deserialized = CookieUtil.deserialize(cookie, String.class);

        // then
        assertEquals(testObject, deserialized);
    }
}
