package yull.todoblog.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import yull.todoblog.article.controller.BlogViewController;
import yull.todoblog.article.domain.Article;
import yull.todoblog.article.service.BlogService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BlogViewControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BlogService blogService;

    @InjectMocks
    private BlogViewController blogViewController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Mockito 주석이 적용된 필드 초기화
        mockMvc = MockMvcBuilders.standaloneSetup(blogViewController).build();
    }

    @Test
    @DisplayName("게시글 전체 조회 테스트")
    public void testGetArticles() throws Exception {
        // given: BlogService가 Article 목록을 반환하도록 모의 설정
        List<Article> articles = Arrays.asList(
                new Article("Title1", "Content1", "Author1"),
                new Article("Title2", "Content2", "Author2")
        );
        when(blogService.findAll()).thenReturn(articles);

        // when: "/articles" 경로로 GET 요청
        mockMvc.perform(get("/articles"))

                // then: 상태 코드가 OK이고, 모델에 "articles" 속성이 존재하며, 뷰 이름이 "articleList"인지 검증
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("articles"))
                .andExpect(view().name("articleList"));
    }

    @Test
    @DisplayName("특정 게시글 조회 테스트")
    public void testGetArticle() throws Exception {
        // given: 특정 ID에 대해 BlogService가 Article 객체를 반환하도록 모의 설정
        Article mockArticle = Article.builder()
                .title("Test Title")
                .content("Test Content")
                .author("Test Author")
                .build();
        when(blogService.findById(anyLong())).thenReturn(mockArticle);

        // when: "/articles/1" 경로로 GET 요청
        mockMvc.perform(get("/articles/1"))

                // then: 상태 코드가 OK이고, 모델에 "article" 속성이 존재하며, 뷰 이름이 "article"인지 검증
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("article"))
                .andExpect(view().name("article"));
    }


    @Test
    @DisplayName("새 게시글 작성 페이지 이동 테스트")
    public void testNewArticle() throws Exception {
        // given: 새로운 게시글 작성을 위한 설정, BlogService 모의 객체 필요 없음

        // when: "/new-article" 경로로 GET 요청
        mockMvc.perform(get("/new-article"))

                // then: 상태 코드가 OK이고, 뷰 이름이 "newArticle"인지 검증
                .andExpect(status().isOk())
                .andExpect(view().name("newArticle"));
    }
}
