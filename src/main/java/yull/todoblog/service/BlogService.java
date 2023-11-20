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

    public Article save(AddArticleRequest request){
        return todoRepository.save(request.toEntitiy());
    }

    public List<Article> findAll(){
        return todoRepository.findAll();
    }
    public Article findById(long id){
        return todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(" 찾을수 없습니다 : " + id ));
    }
    public void delete(long id){
        todoRepository.deleteById(id);
    }
    @Transactional
    public Article update (long id, UpdateArticleRequest request){
        Article article = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(" 찾을수 없습니다 : " + id ));
        article.update(request.getTitle(), request.getContent());
        return  article;
    }
}
