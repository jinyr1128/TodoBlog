package yull.todoblog.dto;

import lombok.Getter;
import yull.todoblog.domain.Article;

@Getter
// ArticleResponse: 단일 게시글 조회 응답을 위한 DTO
public class ArticleResponse {

    private final String title; // 게시글 제목
    private final String content; // 게시글 내용

    // 생성자: Article 엔티티를 DTO로 변환
    public ArticleResponse(Article article) {
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
