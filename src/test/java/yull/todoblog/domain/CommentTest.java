package yull.todoblog.domain;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import yull.todoblog.article.domain.Article;
import yull.todoblog.comment.domain.Comment;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CommentTest {

    @Autowired
    private EntityManager entityManager;

    @Test
    @DisplayName("댓글 생성 테스트")
    public void testCommentCreation() {
        // given: Comment 객체와 연관된 Article 객체 생성
        Article article = new Article("Author", "Title", "Content");
        entityManager.persist(article);

        String commentContent = "This is a test comment";
        Comment comment = new Comment(commentContent, article);

        // when: Comment 객체를 데이터베이스에 저장
        entityManager.persist(comment);
        entityManager.flush();

        // then: Comment 객체가 정상적으로 생성되었는지 검증
        assertNotNull(comment.getId());
        assertEquals(commentContent, comment.getContent());
        assertEquals(article, comment.getArticle());
    }

    @Test
    @DisplayName("댓글 내용 업데이트 테스트")
    public void testUpdateCommentContent() {
        // given: Comment 객체 생성 및 저장
        Article article = new Article("Author", "Title", "Content");
        entityManager.persist(article);

        Comment comment = new Comment("Original Comment", article);
        entityManager.persist(comment);
        entityManager.flush();

        // when: 댓글 내용을 업데이트
        String updatedContent = "Updated Comment";
        comment.updateContent(updatedContent);

        // then: 댓글 내용이 업데이트 되었는지 검증
        assertEquals(updatedContent, comment.getContent());
    }
}
