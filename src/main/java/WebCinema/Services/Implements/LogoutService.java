package WebCinema.Services.Implements;

import WebCinema.Repository.UserRepository;
import WebCinema.Repository.UserStatusRepository;
import WebCinema.Security.Service.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {
    private final UserStatusRepository userStatusRepository;
    private final UserDetailsService userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @Override
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) {
        final var authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        var jwt = authHeader.substring(7);
        var userName = jwtTokenProvider.extractUserName(jwt);
        if (userName != null) {
            //Lay thong tin nguoi dung tu userId // default is load email
            var userDetails = this.userDetailsService.loadUserByUsername(userName);
            var user = userRepository.findByUserName(userDetails.getUsername())
                    .orElseGet(() -> userRepository.findByEmail(userDetails.getUsername())
                            .orElseThrow());
            user.setUserStatus(userStatusRepository
                    .findByCode("False")
                    .orElseThrow(() -> new RuntimeException("Not found UserStatus")));
            userRepository.save(user);
            SecurityContextHolder.clearContext();
        }
    }
}
