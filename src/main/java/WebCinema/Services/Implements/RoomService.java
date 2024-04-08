package WebCinema.Services.Implements;

import WebCinema.Config.ApplicationConfig;
import WebCinema.Repository.RoomRepository;
import WebCinema.Repository.ScheduleRepository;
import WebCinema.Repository.SeatRepository;
import WebCinema.Entity.Room;
import WebCinema.Repository.CinemaRepository;
import WebCinema.Services.Interfaces.IRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomService implements IRoomService {
    private final RoomRepository roomRepository;
    private final CinemaRepository cinemaRepository;
    private final ScheduleRepository scheduleRepository;
    private final SeatRepository seatRepository;
    private final ApplicationConfig config;

    private boolean checkDataExist() {
        return (cinemaRepository.count() > 0);
    }

    @Override
    public Room addNew(Room newRoom) {
        if (!checkDataExist()) return null;
        return roomRepository.save(newRoom);
    }

    @Override
    public Room remake(Room remakeRoom) {
        var current = roomRepository.findById(remakeRoom.getId())
                .orElseThrow(() -> new RuntimeException("Data not found !"));
        BeanUtils.copyProperties(remakeRoom, current, config.getNullPropertyNames(remakeRoom));
        return roomRepository.save(current);
    }

    @Override
    public Room delete(String code) {
        var current = roomRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Data not found !"));
        scheduleRepository.findAll().forEach(x -> {
            if (x.getRooms().getName().equals(code)) x.setRooms(null);
        });
        seatRepository.findAll().forEach(x -> {
            if (x.getRooms().getName().equals(code)) x.setRooms(null);
        });
        roomRepository.delete(current);
        return current;
    }

}
