package WebCinema.Services.Interfaces;

import WebCinema.Entity.Room;

public interface IRoomService {
    Room addNew(Room newRoom);

    Room remake(Room remakeRoom);

    Room delete(String name);
}
