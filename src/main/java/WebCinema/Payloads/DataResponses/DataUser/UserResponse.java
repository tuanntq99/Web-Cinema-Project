package WebCinema.Payloads.DataResponses.DataUser;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    int point;
    String userName;
    String email;
    String name;
    String phoneNumber;
    String roleName;
}
