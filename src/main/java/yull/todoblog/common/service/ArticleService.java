package yull.todoblog.common.service;

import yull.todoblog.common.dto.ArticleRequestDto;
import yull.todoblog.common.dto.ArticleResponseDto;
import yull.todoblog.user.domain.User;

// ArticleService 인터페이스
public interface ArticleService {
    ArticleResponseDto createArticle(ArticleRequestDto requestDto, User user);
}
