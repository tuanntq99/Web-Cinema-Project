package WebCinema.Controller;

import WebCinema.Entity.Food;
import WebCinema.Services.Implements.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class FoodController {
    private final FoodService foodService;

    @PostMapping("/food")
    public ResponseEntity<?> addNew(@RequestBody Food request) {
        return ResponseEntity.ok(foodService.addNew(request));
    }

    @PutMapping("/food")
    public ResponseEntity<?> remake(@RequestBody Food request) {
        return ResponseEntity.ok(foodService.remake(request));
    }

    @DeleteMapping("/food")
    public ResponseEntity<?> delete(@RequestParam String name) {
        return ResponseEntity.ok(foodService.delete(name));
    }

    @GetMapping("/food")
    public ResponseEntity<?> getFoodFeature() {
        return ResponseEntity.ok(foodService.sortFoodByQuantity());
    }

}
