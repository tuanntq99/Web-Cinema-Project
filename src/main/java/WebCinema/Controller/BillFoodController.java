package WebCinema.Controller;

import WebCinema.Entity.BillFood;
import WebCinema.Services.Implements.BillFoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BillFoodController {
    private final BillFoodService billFoodService;

    @PostMapping("/billFood")
    public ResponseEntity<?> addNew(@RequestBody BillFood request) {
        return ResponseEntity.ok(billFoodService.addNew(request));
    }

    @PutMapping("/billFood")
    public ResponseEntity<?> remake(@RequestBody BillFood request) {
        return ResponseEntity.ok(billFoodService.remake(request));
    }

    @PutMapping("/billFood/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return ResponseEntity.ok(billFoodService.delete(id));
    }

}
