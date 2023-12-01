package yull.todoblog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yull.todoblog.domain.Article;
import yull.todoblog.dto.AddArticleRequest;
import yull.todoblog.dto.UpdateArticleRequest;
import yull.todoblog.repository.TodoRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {

    private  final TodoRepository todoRepository;

    // 게시글 저장
    public Article save(AddArticleRequest request, String userName) {
        return todoRepository.save(request.toEntity(userName));
    }

    // 모든 게시글 찾기
    public List<Article> findAll(){
        return todoRepository.findAll();
    }

    // ID로 게시글 찾기
    public Article findById(long id){
        return todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Article not found: " + id));
    }

    // 게시글 삭제
    public void delete(long id) {
        Article article = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        authorizeArticleAuthor(article);
        todoRepository.delete(article);
    }

    // 게시글 업데이트
    @Transactional
    public Article update(long id, UpdateArticleRequest request) {
        Article article = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        authorizeArticleAuthor(article);
        article.update(request.getTitle(), request.getContent());

        return article;
    }

    // 게시글을 작성한 유저인지 확인
    private static void authorizeArticleAuthor(Article article) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!article.getAuthor().equals(userName)) {
            throw new IllegalArgumentException("not authorized");
        }
    }

}
