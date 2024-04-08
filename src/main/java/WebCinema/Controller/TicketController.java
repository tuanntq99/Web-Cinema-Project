package WebCinema.Controller;

import WebCinema.Entity.Ticket;
import WebCinema.Services.Implements.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class TicketController {
    private final TicketService ticketService;

    @PostMapping("/ticket")
    public ResponseEntity<?> addNew(@RequestBody Ticket request) {
        return ResponseEntity.ok(ticketService.addNew(request));
    }

    @PutMapping("/ticket")
    public ResponseEntity<?> remake(@RequestBody Ticket request) {
        return ResponseEntity.ok(ticketService.remake(request));
    }

    @PutMapping("/ticket/{code}")
    public ResponseEntity<?> delete(@PathVariable String code) {
        return ResponseEntity.ok(ticketService.delete(code));
    }

}
