package yull.todoblog.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import yull.todoblog.domain.Article;
import yull.todoblog.domain.Comment;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글에 대한 댓글 조회 테스트")
    public void findByArticleIdTest() {
        // given
        Article article = new Article("Test Author", "Test Title", "Test Content");
        entityManager.persist(article);

        Comment comment1 = new Comment("Test Comment 1", article);
        Comment comment2 = new Comment("Test Comment 2", article);
        entityManager.persist(comment1);
        entityManager.persist(comment2);

        // when
        List<Comment> foundComments = commentRepository.findByArticleId(article.getId());

        // then
        assertEquals(2, foundComments.size());
        assertEquals(comment1.getContent(), foundComments.get(0).getContent());
        assertEquals(comment2.getContent(), foundComments.get(1).getContent());
    }
}
