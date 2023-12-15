package yull.todoblog.dto;

import org.junit.jupiter.api.Test;
import yull.todoblog.article.domain.Article;
import yull.todoblog.article.dto.ArticleListViewResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ArticleListViewResponseTest {

    @Test
    public void testDtoConversion() {
        // given: Article 엔티티 객체 생성 (ID는 설정하지 않음)
        String expectedTitle = "Test Title";
        String expectedContent = "Test Content";
        Article article = Article.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        // when: Article 엔티티를 ArticleListViewResponse DTO로 변환
        ArticleListViewResponse response = new ArticleListViewResponse(article);

        // then: DTO 필드가 Article 엔티티의 데이터와 일치하는지 검증
        assertNotNull(response);
        assertEquals(expectedTitle, response.getTitle());
        assertEquals(expectedContent, response.getContent());

        // ID에 대한 검증은 제외되었습니다.
    }
}