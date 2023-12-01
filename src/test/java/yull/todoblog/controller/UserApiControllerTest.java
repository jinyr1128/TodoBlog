package yull.todoblog.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import yull.todoblog.dto.AddUserRequest;
import yull.todoblog.service.UserService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserApiControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserApiController userApiController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Mockito 어노테이션 초기화
        mockMvc = MockMvcBuilders.standaloneSetup(userApiController).build();
    }
    @Test
    @DisplayName("회원가입 테스트")
    public void testSignup() throws Exception {
        // given
        AddUserRequest request = new AddUserRequest();
        request.setUsername("testUsername");
        request.setEmail("testEmail");
        request.setPassword("testPassword");

        // 가정: save 메서드가 생성된 User 객체의 ID를 반환한다고 가정
        Long expectedUserId = 1L;
        when(userService.save(any(AddUserRequest.class))).thenReturn(expectedUserId);

        // when & then
        mockMvc.perform(post("/user")
                        .param("username", request.getUsername())
                        .param("email", request.getEmail())
                        .param("password", request.getPassword()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    @DisplayName("로그아웃 테스트")
    public void testLogout() throws Exception {
        // when & then
        mockMvc.perform(MockMvcRequestBuilders.get("/logout")) // get 메서드 호출 수정
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }
}
