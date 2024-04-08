package WebCinema.Repository;

import WebCinema.Entity.Bill;
import WebCinema.Entity.BillTicket;
import WebCinema.Entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillTicketRepository extends JpaRepository<BillTicket, Integer> {
    Optional<BillTicket> findBillTicketByBillsAndTickets(Bill bill, Ticket ticket);
}
