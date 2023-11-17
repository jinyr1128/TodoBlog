package yull.todoblog.repository;

import yull.todoblog.domain.Article;

public class ArticleResponse {

    private final String title;
    private final  String content;

    public ArticleResponse(Article article){
        this.title =article.getTitle();
        this.content = article.getContent();
    }
}
