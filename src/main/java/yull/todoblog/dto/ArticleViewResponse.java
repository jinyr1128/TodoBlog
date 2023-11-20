package yull.todoblog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import yull.todoblog.domain.Article;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
// ArticleViewResponse: 게시글 상세 조회 응답을 위한 DTO
public class ArticleViewResponse {

    private Long id; // 게시글 ID
    private String title; // 게시글 제목
    private String content; // 게시글 내용
    private LocalDateTime createdAt; // 생성 시간

    // 생성자: Article 엔티티를 DTO로 변환
    public ArticleViewResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
    }
}