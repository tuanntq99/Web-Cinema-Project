package WebCinema.Services.Interfaces;

import WebCinema.Entity.BillTicket;

public interface IBillTicketService {
    BillTicket addNew(BillTicket newBillTicket);

    BillTicket remake(BillTicket remakeBillTicket);

    BillTicket delete(int id);
}
