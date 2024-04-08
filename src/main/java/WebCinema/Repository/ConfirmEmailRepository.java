package WebCinema.Repository;

import WebCinema.Entity.ConfirmEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmEmailRepository extends JpaRepository<ConfirmEmail, Long> {
    Optional<ConfirmEmail> findByConfirmCode(String otp);
}
