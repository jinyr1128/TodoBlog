package yull.todoblog.service;

import lombok.RequiredArgsConstructor;
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
    public Article save(AddArticleRequest request){
        return todoRepository.save(request.toEntity());
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
    public void delete(long id){
        todoRepository.deleteById(id);
    }

    // 게시글 업데이트
    @Transactional
    public Article update (long id, UpdateArticleRequest request){
        Article article = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(" 찾을수 없습니다 : " + id ));
        article.update(request.getTitle(), request.getContent());
        return  article;
    }
}
