package WebCinema.Exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.security.SignatureException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    ResponseEntity<ProblemDetail> handleSecurityException(Exception ex) {
        log.error("Exception: {}", ex.getMessage());
        var status = ErrorCode.UNCATEGORIZED_EXCEPTION.getStatusCode();
        var reason = ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage();

        if (ex instanceof BadCredentialsException) {
            status = ErrorCode.UNAUTHENTICATED.getStatusCode();
            reason = ErrorCode.UNAUTHENTICATED.getMessage();
        } else if (ex instanceof AccessDeniedException) {
            status = ErrorCode.UNAUTHORIZED.getStatusCode();
            reason = ErrorCode.UNAUTHORIZED.getMessage();
        } else if (ex instanceof AppException appException) {
            status = appException.getErrorCode().getStatusCode();
            reason = appException.getErrorCode().getMessage();
        } else if (ex instanceof MethodArgumentNotValidException validException) {
            var enumKey = validException.getFieldError() != null ? validException.getFieldError().getDefaultMessage() : null;
            var errorCode = ErrorCode.INVALID_KEY;
            try {
                errorCode = ErrorCode.valueOf(enumKey);
            } catch (IllegalArgumentException e) {
                log.error("Exception: {}", e.getMessage());
            }
            status = errorCode.getStatusCode();
            reason = errorCode.getMessage();
        } else if (ex instanceof SignatureException || ex instanceof ExpiredJwtException) {
            status = ex instanceof SignatureException ? ErrorCode.SIGNATURE_INVALID.getStatusCode() : ErrorCode.TOKEN_EXPIRED.getStatusCode();
            reason = ex instanceof SignatureException ? ErrorCode.SIGNATURE_INVALID.getMessage() : ErrorCode.TOKEN_EXPIRED.getMessage();
        }

        var errorDetail = ProblemDetail.forStatusAndDetail(status, ex.getMessage());
        errorDetail.setProperty("access_denied_reason", reason);

        return ResponseEntity.status(errorDetail.getStatus()).body(errorDetail);
    }

}
