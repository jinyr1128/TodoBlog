package yull.todoblog.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import yull.todoblog.article.domain.Article;
import yull.todoblog.article.dto.AddArticleRequest;
import yull.todoblog.article.dto.UpdateArticleRequest;
import yull.todoblog.article.repository.TodoRepository;
import yull.todoblog.article.service.BlogService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class BlogServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private BlogService blogService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    @DisplayName("새로운 게시글을 저장할 때 성공적으로 수행되어야 한다")
    public void testSaveArticle() {
        // given
        AddArticleRequest request = new AddArticleRequest(/* request parameters */);
        String userName = "user";
        Article article = Article.builder()
                .author(userName)
                .title("Test Title")
                .content("Test Content")
                .build();
        when(todoRepository.save(any(Article.class))).thenReturn(article);

        // when
        Article savedArticle = blogService.save(request, userName);

        // then
        assertNotNull(savedArticle);
        assertEquals("Test Title", savedArticle.getTitle());
        assertEquals("Test Content", savedArticle.getContent());
        assertEquals(userName, savedArticle.getAuthor());
    }

    @Test
    @DisplayName("모든 게시글을 조회할 때 리스트를 반환해야 한다")
    public void testFindAllArticles() {
        // given
        Article article1 = Article.builder().author("user1").title("Title1").content("Content1").build();
        Article article2 = Article.builder().author("user2").title("Title2").content("Content2").build();
        when(todoRepository.findAll()).thenReturn(Arrays.asList(article1, article2));

        // when
        List<Article> articles = blogService.findAll();

        // then
        assertNotNull(articles);
        assertEquals(2, articles.size());
    }

    @Test
    @DisplayName("ID로 게시글을 찾을 때 해당 게시글을 반환해야 한다")
    public void testFindById() {
        // given
        long id = 1L;
        Article article = Article.builder().author("user").title("Title").content("Content").build();
        when(todoRepository.findById(id)).thenReturn(Optional.of(article));

        // when
        Article foundArticle = blogService.findById(id);

        // then
        assertNotNull(foundArticle);
        assertEquals("Title", foundArticle.getTitle());
        assertEquals("Content", foundArticle.getContent());
    }

    @Test
    @DisplayName("기존 게시글을 업데이트할 때 성공적으로 수행되어야 한다")
    public void testUpdateArticle() {
        // given
        long id = 1L;
        UpdateArticleRequest request = new UpdateArticleRequest(/* request parameters */);
        String userName = "user";
        Article article = Article.builder().author(userName).title("Old Title").content("Old Content").build();
        when(todoRepository.findById(id)).thenReturn(Optional.of(article));
        when(authentication.getName()).thenReturn(userName);

        // when
        Article updatedArticle = blogService.update(id, request);

        // then
        assertNotNull(updatedArticle);
        // 검증 로직은 UpdateArticleRequest의 내용에 따라 달라질 수 있습니다.
    }

    // delete 메서드에 대한 테스트
    @Test
    @DisplayName("게시글을 삭제할 때 성공적으로 수행되어야 한다")
    public void testDeleteArticle() {
        // given
        long id = 1L;
        String userName = "user";
        Article article = Article.builder().author(userName).title("Title").content("Content").build();
        when(todoRepository.findById(id)).thenReturn(Optional.of(article));
        when(authentication.getName()).thenReturn(userName);

        // when
        blogService.delete(id);

        // then
        verify(todoRepository, times(1)).delete(article);
    }
}
