package WebCinema.Security.Controller;

import WebCinema.Payloads.DataRequests.UserRequest.LoginRequest;
import WebCinema.Payloads.DataRequests.UserRequest.SignUpRequest;
import WebCinema.Payloads.DataRequests.TokenRequest.RefreshTokenRequest;
import WebCinema.Security.Service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.registerUser(request));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authenticationService.loginUser(request));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(authenticationService.refreshToken(request));
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyUser(
            @RequestParam String email,
            @RequestParam String otp
    ) {
        return ResponseEntity.ok(authenticationService.verify(email, otp));
    }

    @PutMapping("/regenerate-otp")
    public ResponseEntity<?> regenerateOtp(@RequestParam String email) {
        return ResponseEntity.ok(authenticationService.regenerateOtp(email));
    }

    @PostMapping("/forgotPassword/{email}")
    public ResponseEntity<?> changePassword(@PathVariable String email) {
        return ResponseEntity.ok(authenticationService.forgotPassword(email));
    }

    @PutMapping("/resetPassword/{otp}")
    public ResponseEntity<?> resetPassword(@PathVariable String otp) {
        return ResponseEntity.ok(authenticationService.resetPassword(otp));
    }

}
