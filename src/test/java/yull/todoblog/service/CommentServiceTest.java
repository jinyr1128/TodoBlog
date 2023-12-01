package yull.todoblog.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import yull.todoblog.domain.Article;
import yull.todoblog.domain.Comment;
import yull.todoblog.repository.CommentRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentService commentService;


    private Article createMockArticle() {
        Article article = mock(Article.class);
        return article;
    }
    @BeforeEach
    public void setup() {
        // 필요한 경우 setup 메서드에서 초기 설정을 수행
    }

    @Test
    public void shouldCreateCommentWithValidData() {
        // given
        Long articleId = 1L;
        String content = "Test Comment";
        Article article = createMockArticle();
        Comment comment = new Comment(content, article);
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        // when
        Comment createdComment = commentService.createComment(articleId, content, article);

        // then
        assertNotNull(createdComment);
        assertEquals(content, createdComment.getContent());
    }

    @Test
    public void shouldUpdateCommentWhenRequested() {
        // given
        Long commentId = 1L;
        String updatedContent = "Updated Comment";
        Comment existingComment = new Comment("Original Comment", createMockArticle());
        when(commentRepository.findById(commentId)).thenReturn(Optional.of(existingComment));
        when(commentRepository.save(any(Comment.class))).thenReturn(existingComment);

        // when
        Comment updatedComment = commentService.updateComment(commentId, updatedContent);

        // then
        assertNotNull(updatedComment);
        assertEquals(updatedContent, updatedComment.getContent());
    }

    @Test
    public void shouldDeleteCommentWhenRequested() {
        // given
        Long commentId = 1L;
        doNothing().when(commentRepository).deleteById(commentId);

        // when
        commentService.deleteComment(commentId);

        // then
        verify(commentRepository, times(1)).deleteById(commentId);
    }
}