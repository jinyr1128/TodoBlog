package yull.todoblog.common.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yull.todoblog.article.service.BlogService;
import yull.todoblog.common.repository.ArticleRepository;
import yull.todoblog.common.dto.ArticleRequestDto;
import yull.todoblog.common.dto.ArticleResponseDto;
import yull.todoblog.common.service.ArticleService;
import yull.todoblog.user.domain.User;
import yull.todoblog.article.domain.Article;


@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;

    @Override
    @Transactional
    public ArticleResponseDto createArticle(ArticleRequestDto requestDto, User user) {
        Article article = new Article(requestDto.getTitle(), requestDto.getContent());
        article.setUser(user);
        articleRepository.save(article);
        return new ArticleResponseDto(article.getId(), article.getTitle(), article.getContent());
    }
}

