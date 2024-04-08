package WebCinema.Controller;

import WebCinema.Entity.Room;
import WebCinema.Services.Implements.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class RoomController {

    private final RoomService roomService;

    @PostMapping("/room")
    public ResponseEntity<?> addNew(@RequestBody Room request) {
        return ResponseEntity.ok(roomService.addNew(request));
    }

    @PutMapping("/room")
    public ResponseEntity<?> remake(@RequestBody Room request) {
        return ResponseEntity.ok(roomService.remake(request));
    }

    @DeleteMapping("/room")
    public ResponseEntity<?> delete(@RequestParam String roomCode) {
        return ResponseEntity.ok(roomService.delete(roomCode));
    }

}
