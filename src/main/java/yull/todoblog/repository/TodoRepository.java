package yull.todoblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yull.todoblog.domain.Article;

public interface TodoRepository extends JpaRepository <Article,Long> {
}
