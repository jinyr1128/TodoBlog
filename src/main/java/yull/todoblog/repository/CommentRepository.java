package yull.todoblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yull.todoblog.domain.Comment;

import java.util.List;

// CommentRepository: 댓글(Comment) 엔티티에 대한 데이터 접근을 처리하는 JPA 리포지토리
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 특정 게시글(Article)에 속하는 댓글들을 찾기 위한 메서드
    List<Comment> findByArticleId(Long articleId);
}

