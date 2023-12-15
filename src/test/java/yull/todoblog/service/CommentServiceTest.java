package yull.todoblog.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import yull.todoblog.article.domain.Article;
import yull.todoblog.comment.service.CommentService;
import yull.todoblog.comment.domain.Comment;
import yull.todoblog.comment.repository.CommentRepository;

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
    @Test
    @DisplayName("댓글 생성이 유효한 데이터로 요청될 때 성공적으로 수행되어야 한다")
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
    @DisplayName("요청된 경우 댓글 업데이트가 성공적으로 수행되어야 한다")
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
    @DisplayName("요청된 경우 댓글 삭제가 성공적으로 수행되어야 한다")
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