package WebCinema.Services.Interfaces;

import WebCinema.Entity.Cinema;

public interface ICinemaService {
    Cinema addNew(Cinema newCinema);

    Cinema remake(Cinema remakeCinema);

    Cinema delete(String name);

}
