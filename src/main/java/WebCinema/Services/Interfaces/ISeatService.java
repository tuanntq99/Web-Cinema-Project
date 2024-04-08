package WebCinema.Services.Interfaces;

import WebCinema.Entity.Seat;

public interface ISeatService {
    Seat addNew(Seat newSeat);

    Seat remake(Seat remakeSeat);

    Seat delete(int id);
}
