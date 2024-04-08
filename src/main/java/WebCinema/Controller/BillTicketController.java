package WebCinema.Controller;

import WebCinema.Entity.BillTicket;
import WebCinema.Services.Implements.BillTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BillTicketController {
    private final BillTicketService billTicketService;

    @PostMapping("/billTicket")
    public ResponseEntity<?> addNew(@RequestBody BillTicket request) {
        return ResponseEntity.ok(billTicketService.addNew(request));
    }

    @PutMapping("/billTicket")
    public ResponseEntity<?> remake(@RequestBody BillTicket request) {
        return ResponseEntity.ok(billTicketService.remake(request));
    }

    @DeleteMapping("/billTicket/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return ResponseEntity.ok(billTicketService.delete(id));
    }

}
