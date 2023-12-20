package yull.todoblog.article.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yull.todoblog.article.domain.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    // 기본적인 CRUD 메서드와 함께 Article 엔티티에 대한 추가적인 메서드 정의
}
