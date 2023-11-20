package yull.todoblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yull.todoblog.domain.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByArticleId(Long articleId);
}
