package yull.todoblog.dto;

import lombok.Getter;
import yull.todoblog.domain.Article;

@Getter
// ArticleListViewResponse: 게시글 목록 조회 응답을 위한 DTO
public class ArticleListViewResponse {

    private final Long id; // 게시글 ID
    private final String title; // 게시글 제목
    private final String content; // 게시글 내용

    // 생성자: Article 엔티티를 DTO로 변환
    public ArticleListViewResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}