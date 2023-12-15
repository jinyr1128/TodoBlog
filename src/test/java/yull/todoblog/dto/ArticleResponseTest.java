package yull.todoblog.dto;

import org.junit.jupiter.api.Test;
import yull.todoblog.article.domain.Article;
import yull.todoblog.article.dto.ArticleResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ArticleResponseTest {

    @Test
    public void testDtoConversion() {
        // given: Article 엔티티 객체 생성
        String expectedTitle = "Test Title";
        String expectedContent = "Test Content";
        Article article = new Article("Test Author", expectedTitle, expectedContent);

        // when: Article 엔티티를 ArticleResponse DTO로 변환
        ArticleResponse response = new ArticleResponse(article);

        // then: DTO 필드가 Article 엔티티의 데이터와 일치하는지 검증
        assertNotNull(response);
        assertEquals(expectedTitle, response.getTitle());
        assertEquals(expectedContent, response.getContent());
    }
}
