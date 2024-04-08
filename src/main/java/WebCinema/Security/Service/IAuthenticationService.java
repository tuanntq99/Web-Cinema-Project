package WebCinema.Security.Service;

import WebCinema.Payloads.DataRequests.TokenRequest.RefreshTokenRequest;
import WebCinema.Payloads.DataRequests.UserRequest.LoginRequest;
import WebCinema.Payloads.DataRequests.UserRequest.SignUpRequest;
import WebCinema.Payloads.DataResponses.DataToken.TokenResponse;
import WebCinema.Payloads.Responses.MessageResponse;

public interface IAuthenticationService {
    String registerUser(SignUpRequest request);

    TokenResponse loginUser(LoginRequest request);

    TokenResponse refreshToken(RefreshTokenRequest request);

    MessageResponse verify(String email, String otp);

    String regenerateOtp(String email);

    MessageResponse forgotPassword(String email);

    MessageResponse resetPassword(String otp);

}
