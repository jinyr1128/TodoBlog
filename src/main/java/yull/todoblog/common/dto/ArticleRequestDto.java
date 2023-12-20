package yull.todoblog.common.dto;

public class ArticleRequestDto {
    private String title; // 게시글 제목
    private String content; // 게시글 내용

    // 생성자, getter 및 setter
    public ArticleRequestDto() {}

    public ArticleRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // Getter 및 Setter
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
