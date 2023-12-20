package yull.todoblog.comment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UpdateCommentRequest {
    private String content; // 수정할 댓글 내용
}
