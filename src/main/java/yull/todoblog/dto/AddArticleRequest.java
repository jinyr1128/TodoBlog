package yull.todoblog.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import yull.todoblog.domain.Article;

@NoArgsConstructor
@AllArgsConstructor
@Getter
// AddArticleRequest: 새로운 게시글 추가 요청을 위한 DTO
public class AddArticleRequest {

    private String title; // 게시글 제목
    private String content; // 게시글 내용

    // DTO를 Article 엔티티로 변환
    public Article toEntity() {
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}
