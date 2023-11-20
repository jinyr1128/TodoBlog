package yull.todoblog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
// UpdateArticleRequest: 게시글 수정 요청을 위한 DTO
public class UpdateArticleRequest {
    private String title; // 수정할 제목
    private String content; // 수정할 내용
}