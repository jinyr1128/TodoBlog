package yull.todoblog.domain;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ArticleTest {

    @Autowired
    private EntityManager entityManager;

    @Test
    public void testArticleCreation() {
        // given: 게시글 생성을 위한 준비
        String title = "Sample Title";
        String content = "Sample Content";
        Article article = Article.builder()
                .title(title)
                .content(content)
                .build();

        // when: 게시글을 데이터베이스에 저장
        entityManager.persist(article);
        entityManager.flush();

        // then: 생성된 게시글의 속성이 예상과 일치하는지 검증
        assertThat(article.getTitle()).isEqualTo(title);
        assertThat(article.getContent()).isEqualTo(content);
        assertThat(article.getCreatedAt()).isBeforeOrEqualTo(LocalDateTime.now());
    }

    @Test
    public void testArticleUpdate() {
        // given: 수정할 게시글 생성 및 초기화
        Article article = new Article("Old Title", "Old Content");
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
