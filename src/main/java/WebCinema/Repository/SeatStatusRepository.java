package WebCinema.Repository;

import WebCinema.Entity.Enum.ESeatStatus;
import WebCinema.Entity.SeatStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeatStatusRepository extends JpaRepository<SeatStatus, Integer> {
    Optional<SeatStatus> findByNameStatus(ESeatStatus name);
}