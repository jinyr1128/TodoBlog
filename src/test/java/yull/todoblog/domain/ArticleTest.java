package yull.todoblog.domain;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import yull.todoblog.article.domain.Article;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ArticleTest {

    @Autowired
    private EntityManager entityManager;

    @Test
    @DisplayName("게시글 생성 테스트")
    public void testArticleCreation() {
        // given: 게시글 생성을 위한 준비
        String author = "Test Author";
        String title = "Sample Title";
        String content = "Sample Content";
        Article article = Article.builder()
                .author(author)
                .title(title)
                .content(content)
                .build();

        // when: 게시글을 데이터베이스에 저장
        entityManager.persist(article);
        entityManager.flush();

        // then: 생성된 게시글의 속성이 예상과 일치하는지 검증
        assertThat(article.getAuthor()).isEqualTo(author);
        assertThat(article.getTitle()).isEqualTo(title);
        assertThat(article.getContent()).isEqualTo(content);
    }
    @Test
    @DisplayName("게시글 수정 테스트")
    public void testArticleUpdate() {
        // given: 수정할 게시글 생성 및 초기화
        Article article = new Article("AuthorName", "Old Title", "Old Content");

        entityManager.persist(article);
        entityManager.flush();

        // when: 게시글 수정
        String updatedTitle = "Updated Title";
        String updatedContent = "Updated Content";
        article.update(updatedTitle, updatedContent);

        // then: 게시글의 수정된 속성이 예상과 일치하는지 검증
        assertThat(article.getTitle()).isEqualTo(updatedTitle);
        assertThat(article.getContent()).isEqualTo(updatedContent);
    }
}
