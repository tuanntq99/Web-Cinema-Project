package WebCinema.Controller;

import WebCinema.Entity.Seat;
import WebCinema.Services.Implements.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class SeatController {
    private final SeatService seatService;

    @PostMapping("/seat")
    public ResponseEntity<?> addNew(@RequestBody Seat request) {
        return ResponseEntity.ok((seatService.addNew(request)));
    }

    @PutMapping("/seat")
    public ResponseEntity<?> remake(@RequestBody Seat request) {
        return ResponseEntity.ok(seatService.remake(request));
    }

    @DeleteMapping("/seat/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return ResponseEntity.ok(seatService.delete(id));
    }
}
