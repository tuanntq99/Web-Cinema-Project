package WebCinema.Services.Interfaces;

import WebCinema.Entity.Ticket;

public interface ITicketService {
    Ticket addNew(Ticket newTicket);

    Ticket remake(Ticket remakeTicket);

    Ticket delete(String code);
}
