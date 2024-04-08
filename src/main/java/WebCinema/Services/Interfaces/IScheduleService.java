package WebCinema.Services.Interfaces;

import WebCinema.Entity.Schedule;

public interface IScheduleService {
    Schedule addNew(Schedule newSchedule);

    Schedule remake(Schedule remakeSchedule);

    Schedule delete(String code);
}
