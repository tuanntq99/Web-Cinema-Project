package WebCinema.Services.Implements;

import WebCinema.Repository.RefreshTokenRepository;
import WebCinema.Repository.UserRepository;
import WebCinema.Entity.RefreshToken;
import WebCinema.Services.Interfaces.IRefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService implements IRefreshTokenService {

    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    @Override
    public RefreshToken createRefreshToken(String username) {
        RefreshToken refreshToken = RefreshToken.builder()
                .users(userRepository.findByUserName(username)
                        .orElseGet(() -> userRepository.findByEmail(username)
                                .orElseThrow(
                                        () -> new NoSuchElementException("User not found")
                                )
                        )
                )
                .token(UUID.randomUUID().toString())
                .expiredTime(new Date(System.currentTimeMillis() + refreshExpiration))//10
                .build();
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

//    @Override
//    public RefreshToken verifyExpiration(RefreshToken token) {
//        if (token.getExpiredTime().compareTo(new Date()) < 0) {
//            throw new RuntimeException(token.getToken() + " Refresh token was expired. Please make a new signin request");
//        }
//        return token;
//    }

}
