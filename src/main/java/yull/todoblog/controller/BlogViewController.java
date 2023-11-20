package yull.todoblog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import yull.todoblog.domain.Article;
import yull.todoblog.dto.ArticleListViewResponse;
import yull.todoblog.dto.ArticleViewResponse;
import yull.todoblog.service.BlogService;

import java.util.List;

// BlogViewController: 웹 페이지 뷰를 제공하는 컨트롤러
@RequiredArgsConstructor
@Controller
public class BlogViewController {

    private final BlogService blogService; // 블로그 서비스

    // 게시글 목록 페이지를 반환하는 메서드
    @GetMapping("/articles")
    public String getArticles(Model model){
        List<ArticleListViewResponse> articles = blogService.findAll().stream()
                .map(ArticleListViewResponse::new)
                .toList();
        model.addAttribute("articles", articles);
        return "articleList"; // articleList 뷰 반환
    }

    // 특정 게시글의 상세 페이지를 반환하는 메서드
    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model){
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleListViewResponse(article));
        return "article"; // article 뷰 반환
    }

    // 새 게시글 작성 페이지를 반환하는 메서드
    @GetMapping("/new-article")
    public String newArticle(@RequestParam(required = false) Long id, Model model) {
        if (id == null) {
            model.addAttribute("article", new ArticleViewResponse());
        } else {
            Article article = blogService.findById(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        }
        return "newArticle"; // newArticle 뷰 반환
    }
}