package yull.todoblog.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import yull.todoblog.article.dto.UpdateArticleRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateArticleRequestTest {

    @Test
    @DisplayName("UpdateArticleRequest 필드 검증 테스트")
    public void testArticleUpdateRequestFields() {
        // given: 수정할 제목과 내용으로 객체 생성
        String expectedTitle = "Updated Title";
        String expectedContent = "Updated Content";
        UpdateArticleRequest request = new UpdateArticleRequest(expectedTitle, expectedContent);

        // when: 생성된 객체에서 제목과 내용 검색
        String actualTitle = request.getTitle();
        String actualContent = request.getContent();

        // then: 객체에 설정된 제목과 내용이 정상적으로 검색되는지 검증
        assertEquals(expectedTitle, actualTitle);
        assertEquals(expectedContent, actualContent);
    }
}
