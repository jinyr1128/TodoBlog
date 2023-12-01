package yull.todoblog.dto;

import org.junit.jupiter.api.Test;
import yull.todoblog.domain.Article;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AddArticleRequestTest {

    @Test
    public void testToEntity() {
        // given: AddArticleRequest 객체와 작성자 정보
        String title = "Test Title";
        String content = "Test Content";
        String author = "Test Author";
        AddArticleRequest request = new AddArticleRequest(title, content);

        // when: DTO를 Article 엔티티로 변환
        Article article = request.toEntity(author);

        // then: 생성된 Article 엔티티의 필드 검증
        assertNotNull(article);
        assertEquals(title, article.getTitle());
        assertEquals(content, article.getContent());
        assertEquals(author, article.getAuthor());
    }
}