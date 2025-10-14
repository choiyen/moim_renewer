package moim.renew.backend.config.Exception;

import moim.renew.backend.config.DTO.ResponseDTO;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GobalExceptionHandler {

    private ResponseDTO responseDTO = new ResponseDTO();

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // 필드별 메시지를 모두 리스트로 추출
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        return ResponseEntity.badRequest()
                .body(responseDTO.Response("error", "Validation Failed", errors));
    }


    // 2️⃣ DB 중복, 파일 선택 등 사용자 정의 예외 처리
    @ExceptionHandler({DuplicateKeyException.class, SelectException.class, InsertException.class})
    public ResponseEntity<?> handleCustomExceptions(Exception ex) {
        return ResponseEntity.badRequest()
                .body(responseDTO.Response("error", ex.getMessage()));
    }


    @ExceptionHandler(EmptyException.class)
    public ResponseEntity<?> handleEmptyException(Exception ex)
    {
        return ResponseEntity.badRequest().body(responseDTO.Response("empty", ex.getMessage()));
    }
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<String> handleMaxSizeException(MaxUploadSizeExceededException ex) {
        return ResponseEntity.badRequest().body("파일 크기가 너무 큽니다. (최대 20MB)");
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllExceptions(Exception ex) {
        return ResponseEntity.badRequest().body(responseDTO.Response("error", ex.getMessage()));
    }

}
