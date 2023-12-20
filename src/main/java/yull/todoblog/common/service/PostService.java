package yull.todoblog.common.service;


import yull.todoblog.article.dto.ArticleResponse;
import yull.todoblog.common.dto.ArticleRequestDto;

import yull.todoblog.user.domain.User;

public interface PostService {
    /**
     * 게시글 생성
     * @param requestDto 게시글 생성 요청 정보
     * @param user 게시글 생성 요청자
     * @return 게시글 생성 결과
     */
    ArticleResponse createPost(ArticleRequestDto requestDto, User user);
}
