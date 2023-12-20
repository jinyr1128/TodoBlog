package yull.todoblog.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yull.todoblog.article.domain.Article;
import yull.todoblog.article.repository.ArticleRepository;
import yull.todoblog.comment.domain.Comment;
import yull.todoblog.comment.repository.CommentRepository;
@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository; // 추가

    public Comment createComment(Long articleId, String content) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("Article not found: " + articleId));
        Comment comment = new Comment(content, article);
        return commentRepository.save(comment);
    }



    // 댓글 수정
    public Comment updateComment(Long commentId, String content) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found: " + commentId));
        comment.updateContent(content);
        return commentRepository.save(comment);
    }

    // 댓글 삭제
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
