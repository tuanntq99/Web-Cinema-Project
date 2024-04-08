package WebCinema.Controller;

import WebCinema.Entity.Enum.ESeatStatus;
import WebCinema.Entity.Movie;
import WebCinema.Services.Implements.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class MovieController {
    private final MovieService movieService;

    @PostMapping("/movie")
    public ResponseEntity<?> addNew(@RequestBody Movie request) {
        return ResponseEntity.ok(movieService.addNew(request));
    }

    @PutMapping("/movie")
    public ResponseEntity<?> remake(@RequestBody Movie request) {
        return ResponseEntity.ok(movieService.remake(request));
    }

    @DeleteMapping("/movie")
    public ResponseEntity<?> delete(@RequestParam String name) {
        return ResponseEntity.ok(movieService.delete(name));
    }

    @GetMapping("/movie/getByCinema/{page}/{size}/{name}")
    public ResponseEntity<?> getMovieByCinema(
            @PathVariable String name,
            @PathVariable int page,
            @PathVariable int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(movieService.getMovieByCinema(pageable, name));
    }

    @GetMapping("/movie/getByRoom/{page}/{size}/{code}")
    public ResponseEntity<?> getMovieByRoom(
            @PathVariable String code,
            @PathVariable int page,
            @PathVariable int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(movieService.getMovieByRoom(pageable, code));
    }

    @GetMapping("/movie/getBySeatStatus/{page}/{size}/{name}")
    public ResponseEntity<?> getMovieBySeatStatus(
            @PathVariable ESeatStatus name,
            @PathVariable int page,
            @PathVariable int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(movieService.getMovieBySeatStatus(pageable, name));
    }

    @GetMapping("/movie/sortMovieByTicket")
    public ResponseEntity<?> sortMovieByTicket() {
        return ResponseEntity.ok(movieService.sortMovieByTicket());
    }

}
