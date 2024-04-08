package WebCinema.Controller;

import WebCinema.Payloads.DataRequests.UserRequest.ChangePasswordRequest;
import WebCinema.Payloads.DataRequests.UserRequest.ChangeRoleRequest;
import WebCinema.Payloads.DataRequests.UserRequest.UpdateUserRequest;
import WebCinema.Services.Implements.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PutMapping("/user/changePassword")
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest request,
            Principal connectedUser
    ) {
        return ResponseEntity.ok(service.changePassword(request, connectedUser));
    }

    @PutMapping("/user/changeRole")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> changeRole(@RequestBody ChangeRoleRequest request) {
        return ResponseEntity.ok(service.changeRole(request));
    }

    @PutMapping("/user/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserRequest request) {
        return ResponseEntity.ok(service.updateUser(request));
    }

    @PutMapping("/user/delete/{email}")
    public ResponseEntity<?> delete(@PathVariable String email) {
        return ResponseEntity.ok(service.delete(email));
    }

    @GetMapping("/user/getAllUser")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }

    @GetMapping("/user/getById")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<?> getById(@RequestParam long userId) {
        return ResponseEntity.ok(service.getById(userId));
    }

}
