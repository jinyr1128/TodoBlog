package yull.todoblog.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import yull.todoblog.article.domain.Article;
import yull.todoblog.article.repository.TodoRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("TodoRepository Test")
public class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    @DisplayName("게시글 생성 및 조회 테스트")
    public void testCreateAndFindArticle() {
        // given
        Article newArticle = new Article("Test Author", "Test Title", "Test Content");

        // when
        Article savedArticle = todoRepository.save(newArticle);
        Optional<Article> foundArticle = todoRepository.findById(savedArticle.getId());

        // then
        assertTrue(foundArticle.isPresent());
        assertEquals("Test Author", foundArticle.get().getAuthor());
        assertEquals("Test Title", foundArticle.get().getTitle());
        assertEquals("Test Content", foundArticle.get().getContent());
    }

    @Test
    @DisplayName("게시글 삭제 테스트")
    public void testDeleteArticle() {
        // given
        Article newArticle = new Article("Test Author", "Test Title", "Test Content");
        Article savedArticle = todoRepository.save(newArticle);

        // when
        todoRepository.deleteById(savedArticle.getId());
        Optional<Article> foundArticle = todoRepository.findById(savedArticle.getId());

        // then
        assertFalse(foundArticle.isPresent());
    }

    @Test
    @DisplayName("게시글 수정 테스트")
    public void testUpdateArticle() {
        // given
        Article newArticle = new Article("Original  Author", "Original Title", "Test Content");
        Article savedArticle = todoRepository.save(newArticle);

        // when
        savedArticle.update("Updated Title", "Updated Content"); // update 메서드 사용
        todoRepository.save(savedArticle);

        Optional<Article> foundArticle = todoRepository.findById(savedArticle.getId());

        // then
        assertTrue(foundArticle.isPresent());
        assertEquals("Updated Title", foundArticle.get().getTitle());
        assertEquals("Updated Content", foundArticle.get().getContent());
    }
}
