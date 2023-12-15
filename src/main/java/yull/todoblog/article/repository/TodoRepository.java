package yull.todoblog.article.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yull.todoblog.article.domain.Article;

// TodoRepository: 할 일(Article) 엔티티에 대한 데이터 접근을 처리하는 JPA 리포지토리
public interface TodoRepository extends JpaRepository <Article, Long> {
    // Article 엔티티에 대한 기본적인 CRUD 연산을 제공
}
