package yull.todoblog.comment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yull.todoblog.article.domain.Article;
import yull.todoblog.comment.domain.Comment;
import yull.todoblog.comment.dto.CreateCommentRequest;
import yull.todoblog.comment.dto.UpdateCommentRequest;
import yull.todoblog.comment.service.CommentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;

    // 댓글 작성 API
    @PostMapping("/articles/{articleId}/comments")
    public ResponseEntity<Comment> createComment(@PathVariable Long articleId,
                                                 @RequestBody CreateCommentRequest request) {
        Comment newComment = commentService.createComment(articleId, request.getContent());
        return ResponseEntity.status(HttpStatus.CREATED).body(newComment);
    }


    // 댓글 수정 API
    @PutMapping("/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long commentId,
                                                 @RequestBody UpdateCommentRequest request) {
        Comment updatedComment = commentService.updateComment(commentId, request.getContent());
        return ResponseEntity.ok(updatedComment);
    }

    // 댓글 삭제 API
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok().build();
    }
}
