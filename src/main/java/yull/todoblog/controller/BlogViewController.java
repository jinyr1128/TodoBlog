package yull.todoblog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import yull.todoblog.dto.ArticleListViewResponse;
import yull.todoblog.service.BlogService;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {

    private  final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model){
        List<ArticleListViewResponse> articles = blogService.findAll().stream()
                .map(ArticleListViewResponse::new)
                .toList();
        model.addAttribute("articles",articles);

        return "articleList";
    }
}
