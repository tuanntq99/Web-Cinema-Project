package WebCinema.Controller;

import WebCinema.Entity.Promotion;
import WebCinema.Services.Implements.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class PromotionController {
    private final PromotionService promotionService;

    @PostMapping("/promotion")
    public ResponseEntity<?> addNew(@RequestBody Promotion request) {
        return ResponseEntity.ok(promotionService.addNew(request));
    }

    @PutMapping("/promotion")
    public ResponseEntity<?> remake(@RequestBody Promotion request) {
        return ResponseEntity.ok(promotionService.remake(request));
    }

    @PutMapping("/promotion/{type}")
    public ResponseEntity<?> delete(@PathVariable String type) {
        return ResponseEntity.ok(promotionService.delete(type));
    }

}
