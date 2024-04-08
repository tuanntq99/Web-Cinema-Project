package WebCinema.Services.Implements;

import WebCinema.Config.ApplicationConfig;
import WebCinema.Repository.*;
import WebCinema.Entity.BillTicket;
import WebCinema.Entity.Enum.ESeatStatus;
import WebCinema.Entity.Movie;
import WebCinema.Payloads.DataResponses.DataMovie.MovieResponse;
import WebCinema.Payloads.DataResponses.DataMovie.SortByTicket;
import WebCinema.Services.Interfaces.IMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService implements IMovieService {
    private final SeatStatusRepository seatStatusRepository;
    private final MovieTypeRepository movieTypeRepository;
    private final ScheduleRepository scheduleRepository;
    private final CinemaRepository cinemaRepository;
    private final MovieRepository movieRepository;
    private final RateRepository rateRepository;
    private final RoomRepository roomRepository;
    private final ApplicationConfig config;

    public boolean checkDataExist() {
        return (rateRepository.count() > 0
                || movieTypeRepository.count() > 0);
    }

    @Override
    public Movie addNew(Movie newMovie) {
        if (!checkDataExist()) return null;
        return movieRepository.save(newMovie);
    }

    @Override
    public Movie remake(Movie remakeMovie) {
        var current = movieRepository.findById(remakeMovie.getId())
                .orElseThrow(() -> new RuntimeException("Movie request is not in database !"));
        BeanUtils.copyProperties(remakeMovie, current, config.getNullPropertyNames(remakeMovie));
        return movieRepository.save(current);
    }

    @Override
    public Movie delete(String name) {
        var current = movieRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Data not found !"));
        scheduleRepository.findAll().forEach(x -> {
            if (x.getMovies().getName().equals(name))
                x.setMovies(null);
        });
        movieRepository.delete(current);
        return current;
    }

    // movie appear again. fix this bug
    // Solution: use DISTINCT in @Query
    @Override
    public Page<MovieResponse> getMovieByCinema(Pageable pageable, String name) {
        var current = cinemaRepository.findByNameOfCinema(name)
                .orElseThrow(() -> new RuntimeException("Data not found"));
        return movieRepository.findAllMovieByCinema(current.getId(), pageable);
    }

    // movie appear again. fix this bug
    // Solution: use DISTINCT in @Query
    @Override
    public Page<MovieResponse> getMovieByRoom(Pageable pageable, String code) {
        var current = roomRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Data not found !"));
        return movieRepository.findAllMovieByRoom(current.getId(), pageable);
    }

    // movie appear again. fix this bug
    // Solution: use DISTINCT in @Query
    @Override
    public Page<MovieResponse> getMovieBySeatStatus(Pageable pageable, ESeatStatus name) {
        var current = seatStatusRepository.findByNameStatus(name)
                .orElseThrow(() -> new RuntimeException("Data not found"));
        return movieRepository.findAllMovieBySeatStatus(current.getId(), pageable);
    }

    @Override
    public List<SortByTicket> sortMovieByTicket() {
        List<SortByTicket> obj = new ArrayList<>();
        movieRepository.findAll().forEach(x -> {
            var id = x.getId();
            var name = x.getName();
            var quantity = x.getSchedules().stream()
                    .flatMap(y -> y.getTickets().stream()
                            .flatMap(z -> z.getBillTickets().stream()
                                    .mapToInt(BillTicket::getQuantity)
                                    .boxed()))
                    .mapToInt(Integer::intValue)
                    .sum();
            SortByTicket newList = new SortByTicket(id, name, quantity);
            obj.add(newList);
        });
        return obj.stream()
                .sorted(Comparator.comparingInt(SortByTicket::getQuantity).reversed())
                .toList();
    }
}
