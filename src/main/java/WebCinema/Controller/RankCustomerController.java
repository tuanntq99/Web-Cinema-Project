package WebCinema.Controller;

import WebCinema.Entity.RankCustomer;
import WebCinema.Services.Implements.RankCustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class RankCustomerController {
    private final RankCustomerService rankCustomerService;

    @PostMapping("/rankCustomer")
    public ResponseEntity<?> addNew(@RequestBody RankCustomer request) {
        return ResponseEntity.ok(rankCustomerService.addNew(request));
    }

    @PutMapping("/rankCustomer")
    public ResponseEntity<?> remake(@RequestBody RankCustomer request) {
        return ResponseEntity.ok(rankCustomerService.remake(request));
    }

    @PutMapping("/rankCustomer/{name}")
    public ResponseEntity<?> delete(@PathVariable String name) {
        return ResponseEntity.ok(rankCustomerService.delete(name));
    }

}
