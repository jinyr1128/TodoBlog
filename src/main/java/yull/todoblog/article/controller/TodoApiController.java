package yull.todoblog.article.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yull.todoblog.common.config.jwt.TokenProvider;
import yull.todoblog.article.domain.Article;
import yull.todoblog.article.dto.AddArticleRequest;
import yull.todoblog.article.dto.UpdateArticleRequest;
import yull.todoblog.article.dto.ArticleResponse;
import yull.todoblog.article.service.BlogService;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
// TodoApiController: 할 일(Todo) 관련 API 요청을 처리하는 컨트롤러
public class TodoApiController {

    private final BlogService blogService;
    private final TokenProvider tokenProvider; // 토큰 제공자

    // 새 게시글 추가 API

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request, Principal principal) {
        Article savedArticle = blogService.save(request, principal.getName());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }


    // 게시글 목록 조회 API
    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();
        return ResponseEntity.ok().body(articles);
    }

    // 특정 게시글 조회 API
    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id) {
        Article article = blogService.findById(id);
        return ResponseEntity.ok().body(new ArticleResponse(article));
    }

    // 게시글 삭제 API
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id) {
        blogService.delete(id);
        return ResponseEntity.ok().build();
    }

    // 게시글 수정 API
    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id, @RequestBody UpdateArticleRequest request) {
        Article updatedArticle = blogService.update(id, request);
        return ResponseEntity.ok().body(updatedArticle);
    }
}
