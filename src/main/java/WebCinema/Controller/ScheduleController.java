package WebCinema.Controller;

import WebCinema.Entity.Schedule;
import WebCinema.Services.Implements.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/schedule")
    public ResponseEntity<?> addNew(@RequestBody Schedule request) {
        return ResponseEntity.ok(scheduleService.addNew(request));
    }

    @PutMapping("/schedule")
    public ResponseEntity<?> remake(@RequestBody Schedule request) {
        return ResponseEntity.ok(scheduleService.remake(request));
    }

    @PutMapping("/schedule/{code}")
    public ResponseEntity<?> delete(@PathVariable String code) {
        return ResponseEntity.ok(scheduleService.delete(code));
    }
}
