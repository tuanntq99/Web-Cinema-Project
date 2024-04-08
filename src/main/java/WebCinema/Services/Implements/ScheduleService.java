package WebCinema.Services.Implements;

import WebCinema.Config.ApplicationConfig;
import WebCinema.Repository.RoomRepository;
import WebCinema.Repository.ScheduleRepository;
import WebCinema.Repository.TicketRepository;
import WebCinema.Entity.Schedule;
import WebCinema.Services.Interfaces.IScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService implements IScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final RoomRepository roomRepository;
    private final TicketRepository ticketRepository;
    private final ApplicationConfig config;

    @Override
    public Schedule addNew(Schedule newSchedule) {
        return scheduleRepository.save(newSchedule);
    }
//    @Override
//    public Schedule addNew(Schedule newSchedule) {
//        scheduleRepository.save(newSchedule);
//        var current = scheduleRepository.findByCode(newSchedule.getCode())
//                .orElseThrow(() -> new RuntimeException("Data not found"));
//        final boolean[] conflictFound = {false};
//        roomRepository.findAll()
//                .forEach(room -> room.getSchedules().forEach(schedule -> {
//                    if (schedule.getRooms().equals(current.getRooms())
//                            && schedule.getMovies().equals(current.getMovies())
//                            && !current.getCode().equals(schedule.getCode())
//                            && ((schedule.getEndAt().compareTo(current.getStartAt()) > 0
//                            && current.getStartAt().compareTo(schedule.getEndAt()) > 0)
//                            || (schedule.getEndAt().compareTo(current.getEndAt()) > 0
//                            && current.getEndAt().compareTo(schedule.getStartAt()) > 0))
//                    ) {
//                            newSchedule.setName("ok");
//                            conflictFound[0] = true;
//                    }
//                }));
//        if (conflictFound[0]) {
//            scheduleRepository.delete(current);
////            newSchedule.setName("delete");
////            scheduleRepository.save(newSchedule);
//        }
//        return newSchedule;
//    }

//    @Override
//    public Schedule addNew(Schedule newSchedule) {
//        scheduleRepository.save(newSchedule);
//        if (checkTimeConflict(newSchedule)) {
//            scheduleRepository.delete(
//                    scheduleRepository.findByCode(newSchedule.getCode()).orElseThrow()
//            );
//            newSchedule.setName("delete");
//        }
//        return newSchedule;
//    }


    @Override
    public Schedule remake(Schedule remakeSchedule) {
        var current = scheduleRepository.findById(remakeSchedule.getId())
                .orElseThrow(() -> new RuntimeException("Data not found"));
//        if (checkTimeConflict(remakeSchedule)) return null;
        BeanUtils.copyProperties(remakeSchedule, current, config.getNullPropertyNames(remakeSchedule));
        return scheduleRepository.save(current);
    }

    public boolean checkTimeConflict(Schedule checkSchedule) {
        return roomRepository.findAll().stream()
                .flatMap(room -> room.getSchedules().stream())
                .anyMatch(schedule ->
                                schedule.getRooms().equals(checkSchedule.getRooms())
                                        && schedule.getMovies().equals(checkSchedule.getMovies())
//                                && ((schedule.getEndAt().compareTo(checkSchedule.getStartAt()) >= 0
//                                && checkSchedule.getStartAt().compareTo(schedule.getEndAt()) >= 0)
//                                || (schedule.getEndAt().compareTo(checkSchedule.getEndAt()) >= 0
//                                && checkSchedule.getEndAt().compareTo(schedule.getStartAt()) >= 0))
                );
    }

    @Override
    public Schedule delete(String code) {
        var current = scheduleRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Data not found"));
        ticketRepository.findAll().forEach(x -> {
            if (x.getSchedules().getCode().equals(code))
                x.setActive(false);
        });
        current.setActive(false);
        return current;
    }
}
