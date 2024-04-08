package WebCinema.Controller;

import WebCinema.Entity.Cinema;
import WebCinema.Services.Implements.CinemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class CinemaController {

    private final CinemaService cinemaService;

    @PostMapping("/cinema")
    public ResponseEntity<?> addNew(@RequestBody Cinema request) {
        return ResponseEntity.ok(cinemaService.addNew(request));
    }

    @PutMapping("/cinema")
    public ResponseEntity<?> remake(@RequestBody Cinema request) {
        return ResponseEntity.ok(cinemaService.remake(request));
    }

    @DeleteMapping("/cinema")
    public ResponseEntity<?> delete(@RequestParam String cinemaName) {
        return ResponseEntity.ok(cinemaService.delete(cinemaName));
    }

}
