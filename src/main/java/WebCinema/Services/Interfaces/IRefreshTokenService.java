package WebCinema.Services.Interfaces;

import WebCinema.Entity.RefreshToken;

import java.util.Optional;

public interface IRefreshTokenService {
    RefreshToken createRefreshToken(String username);
    Optional<RefreshToken> findByToken(String token);
//    RefreshToken verifyExpiration(RefreshToken token);
}
