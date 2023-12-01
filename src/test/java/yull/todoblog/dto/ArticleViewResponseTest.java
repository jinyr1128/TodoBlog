package yull.todoblog.dto;

import org.junit.jupiter.api.Test;
import yull.todoblog.domain.Article;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ArticleViewResponseTest {

    @Test
    public void testDtoConversion() {
        // given: Article 엔티티 객체 생성
        String expectedTitle = "Test Title";
        String expectedContent = "Test Content";
        String expectedAuthor = "Test Author";
        Article article = Article.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .author(expectedAuthor)
                .build();

        // when: Article 엔티티를 ArticleViewResponse DTO로 변환
        ArticleViewResponse response = new ArticleViewResponse(article);

        // then: DTO 필드가 Article 엔티티의 데이터와 일치하는지 검증
        assertNotNull(response);
        assertEquals(expectedTitle, response.getTitle());
        assertEquals(expectedContent, response.getContent());
        assertEquals(expectedAuthor, response.getAuthor());
    }
}
