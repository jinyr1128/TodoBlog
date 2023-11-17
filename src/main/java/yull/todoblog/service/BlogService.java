package yull.todoblog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yull.todoblog.domain.Article;
import yull.todoblog.dto.AddArticleRequest;
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
}
