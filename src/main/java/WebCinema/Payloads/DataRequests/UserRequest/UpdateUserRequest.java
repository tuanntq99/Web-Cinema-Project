package WebCinema.Payloads.DataRequests.UserRequest;

import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateUserRequest {
    long id;
    @Min(value = 0, message = "Value must be greater than or equal to 0")
    @Max(value = 100, message = "Value must be less than or equal to 100")
    int point;
    @Size(min = 3, max = 20, message = "USERNAME_INVALID")
    String userName;
    @Size(min = 1, max = 40, message = "EMAIL_INVALID")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "EMAIL_INVALID")
    String email;
    @Size(min = 3, max = 20)
    String name;
    @Pattern(regexp = "0[0-9]{9}", message = "PHONE_NUMBER_INVALID")
    String phoneNumber;
    @Size(min = 6, max = 40, message = "PASSWORD_INVALID")
    String password;
}
