package WebCinema.Controller;

import WebCinema.Payloads.DataRequests.BillRequest.BillRequest;
import WebCinema.Services.Implements.BillService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BillController {
    private final BillService billService;

    @PostMapping("/bill")
    public ResponseEntity<?> addNew(@RequestBody BillRequest newRequest,
                                    HttpServletRequest request) {
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

        return ResponseEntity.ok(billService.addNew(newRequest, baseUrl));
    }

    @PutMapping("/bill")
    public ResponseEntity<?> remake(@RequestBody BillRequest remakeRequest,
                                    HttpServletRequest request) {
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

        return ResponseEntity.ok(billService.remake(remakeRequest, baseUrl));
    }

    @PutMapping("/bill/{name}")
    public ResponseEntity<?> delete(@PathVariable String name) {
        return ResponseEntity.ok(billService.delete(name));
    }
}
