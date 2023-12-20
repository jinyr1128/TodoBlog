package yull.todoblog.common.dto;

public class ArticleResponseDto {
    private Long id; // 게시글 ID
    private String title; // 게시글 제목
    private String content; // 게시글 내용

    // 생성자, getter 및 setter
    public ArticleResponseDto() {}

    public ArticleResponseDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    // Getter 및 Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
