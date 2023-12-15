package yull.todoblog.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yull.todoblog.article.domain.Article;
import yull.todoblog.comment.domain.Comment;
import yull.todoblog.comment.repository.CommentRepository;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    // 댓글 작성
    public Comment createComment(Long articleId, String content, Article article) {
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
