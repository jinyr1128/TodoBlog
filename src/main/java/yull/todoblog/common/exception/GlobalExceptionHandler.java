package yull.todoblog.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

// GlobalExceptionHandler: 애플리케이션 전체에서 발생하는 예외를 처리하는 클래스
@ControllerAdvice
public class GlobalExceptionHandler {

    // IllegalArgumentException에 대한 예외 처리 핸들러
    @ExceptionHandler(value = { IllegalArgumentException.class })
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage(); // 예외 메시지를 추출

        // 상태코드 400 (BAD_REQUEST)로 응답하고, 예외 메시지를 응답 본문에 포함
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bodyOfResponse);
    }
}
