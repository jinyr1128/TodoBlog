package yull.todoblog.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yull.todoblog.article.domain.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
