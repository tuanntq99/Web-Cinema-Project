package WebCinema.Services.Interfaces;

import WebCinema.Entity.Enum.ESeatStatus;
import WebCinema.Entity.Movie;
import WebCinema.Payloads.DataResponses.DataMovie.MovieResponse;
import WebCinema.Payloads.DataResponses.DataMovie.SortByTicket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IMovieService {
    Movie addNew(Movie newMovie);

    Movie remake(Movie remakeMovie);

    Movie delete(String name);

    Page<MovieResponse> getMovieByCinema(Pageable pageable, String name);

    Page<MovieResponse> getMovieByRoom(Pageable pageable, String code);

    Page<MovieResponse> getMovieBySeatStatus(Pageable pageable, ESeatStatus name);

    List<SortByTicket> sortMovieByTicket();

}
