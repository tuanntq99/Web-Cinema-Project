package WebCinema.Services.Interfaces;

import WebCinema.Payloads.DataRequests.BillRequest.BillRequest;
import WebCinema.Payloads.Responses.MessageResponse;

public interface IBillService {
    String addNew(BillRequest newBill, String request);

    String remake(BillRequest remakeBill, String request);

    MessageResponse delete(String name);
}
