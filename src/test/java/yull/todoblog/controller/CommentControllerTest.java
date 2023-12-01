package yull.todoblog.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import yull.todoblog.service.CommentService;
import yull.todoblog.domain.Article;
import yull.todoblog.domain.Comment;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CommentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentController commentController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Mockito 주석 초기화
        this.mockMvc = MockMvcBuilders.standaloneSetup(commentController).build();
    }

    @Test
    public void testCreateComment() throws Exception {
        // given
        String content = "Test Comment Content";
        Article mockArticle = Article.builder()
                .title("Test Title")
                .content("Test Content")
                .author("Test Author")
                .build();
        Comment mockComment = new Comment(content, mockArticle);
        when(commentService.createComment(anyLong(), anyString(), any(Article.class))).thenReturn(mockComment);

        // when & then
        mockMvc.perform(post("/api/articles/1/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.content").value(content));
    }

    @Test
    public void testUpdateComment() throws Exception {
        // given
        String updatedContent = "Updated Comment Content";
        // Assuming the Article class has a builder pattern
        Article mockArticle = Article.builder()
                .title("Sample Title")
                .content("Sample Content")
                .author("Author Name")
                .build();
        Comment updatedComment = new Comment(updatedContent, mockArticle);
        when(commentService.updateComment(anyLong(), anyString())).thenReturn(updatedComment);

        // when & then
        mockMvc.perform(put("/api/comments/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value(updatedContent));
    }


    @Test
    public void testDeleteComment() throws Exception {
        // given
        doNothing().when(commentService).deleteComment(anyLong());

        // when & then
        mockMvc.perform(delete("/api/comments/1"))
                .andExpect(status().isOk());
    }
}
