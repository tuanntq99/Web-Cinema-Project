package WebCinema.Repository;

import WebCinema.Entity.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserStatusRepository extends JpaRepository<UserStatus,Integer> {
    Optional<UserStatus> findByCode(String code);
}
