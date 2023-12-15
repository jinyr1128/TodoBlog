package yull.todoblog.comment.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import yull.todoblog.article.domain.Article;

@Entity
@Getter
@NoArgsConstructor
// Comment: 댓글에 관한 엔티티 클래스
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 고유 ID

    @Column(nullable = false)
    private String content; // 댓글 내용

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article; // 해당 댓글이 속한 게시글

    // 생성자
    public Comment(String content, Article article) {
        this.content = content;
        this.article = article;
    }

    // 댓글 내용 수정 메서드
    public void updateContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

}

