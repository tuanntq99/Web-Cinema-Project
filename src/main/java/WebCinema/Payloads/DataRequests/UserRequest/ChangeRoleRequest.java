package WebCinema.Payloads.DataRequests.UserRequest;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChangeRoleRequest {
    @Size(max = 50)
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$")
    String email;
    @NonNull
    String role;
}
