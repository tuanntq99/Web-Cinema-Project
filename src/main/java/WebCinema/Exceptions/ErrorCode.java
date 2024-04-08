package WebCinema.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Uncategorized error", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "User existed", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1003, "Username must be between 3 and 20 characters", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1004, "Password must be between 6 and 40 characters", HttpStatus.BAD_REQUEST),
    NOT_FILLED_IN(1005, "Information is not filled in completely", HttpStatus.BAD_REQUEST),
    EMAIL_INVALID(1006, "Email format is invalid", HttpStatus.BAD_REQUEST),
    EMAIL_EXISTED(1007, "Email already existed", HttpStatus.BAD_REQUEST),
    PHONE_NUMBER_INVALID(1008, "PhoneNumber format is invalid", HttpStatus.BAD_REQUEST),
    PHONE_NUMBER_EXISTED(1009, "PhoneNumber already existed", HttpStatus.BAD_REQUEST),
    DATA_NOT_FOUND(1010, "Data not found", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1011, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1012, "You do not have permission", HttpStatus.FORBIDDEN),
    SIGNATURE_INVALID(1013,"JWT Signature not valid",HttpStatus.FORBIDDEN),
    TOKEN_EXPIRED(1014,"JWT Token already expired",HttpStatus.FORBIDDEN),
    ;

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;

}
