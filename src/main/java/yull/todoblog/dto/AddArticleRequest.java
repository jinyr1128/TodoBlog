package yull.todoblog.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import yull.todoblog.domain.Article;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest {

    private String title;
    private String content;


    public Article toEntitiy(){
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}
