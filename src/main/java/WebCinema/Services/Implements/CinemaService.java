package WebCinema.Services.Implements;

import WebCinema.Repository.RoomRepository;
import WebCinema.Config.ApplicationConfig;
import WebCinema.Entity.Cinema;
import WebCinema.Repository.CinemaRepository;
import WebCinema.Services.Interfaces.ICinemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CinemaService implements ICinemaService {
    private final CinemaRepository cinemaRepository;
    private final RoomRepository roomRepository;
    private final ApplicationConfig config;

    @Override
    public Cinema addNew(Cinema newCinema) {
        return cinemaRepository.save(newCinema);
    }

    @Override
    public Cinema remake(Cinema remakeCinema) {
        var currentCinema = cinemaRepository.findById(remakeCinema.getId())
                .orElseThrow(() -> new RuntimeException("Cinema request is not in database !"));
        // copy value except null
        BeanUtils.copyProperties(remakeCinema, currentCinema, config.getNullPropertyNames(remakeCinema));
        return cinemaRepository.save(currentCinema);
    }

    @Override
    public Cinema delete(String name) {
        var delCinema = cinemaRepository.findByNameOfCinema(name)
                .orElseThrow(() -> new RuntimeException("Cinema request is not in database !"));
        roomRepository.findAll().forEach(x -> {
            if (x.getCinema().getNameOfCinema().equals(name))
                x.setCinema(null);
        });
        cinemaRepository.delete(delCinema);
        return delCinema;
    }

}

