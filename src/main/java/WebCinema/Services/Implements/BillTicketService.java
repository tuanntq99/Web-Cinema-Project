package WebCinema.Services.Implements;

import WebCinema.Config.ApplicationConfig;
import WebCinema.Entity.BillTicket;
import WebCinema.Repository.BillTicketRepository;
import WebCinema.Services.Interfaces.IBillTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BillTicketService implements IBillTicketService {
    private final BillTicketRepository billTicketRepository;
    private final ApplicationConfig config;

    @Override
    public BillTicket addNew(BillTicket newBillTicket) {
        return billTicketRepository.save(newBillTicket);
    }

    @Override
    public BillTicket remake(BillTicket remakeBillTicket) {
        var current = billTicketRepository.findById(remakeBillTicket.getId())
                .orElseThrow(() -> new RuntimeException("Data not found"));
        BeanUtils.copyProperties(remakeBillTicket, current, config.getNullPropertyNames(remakeBillTicket));
        return billTicketRepository.save(current);
    }

    @Override
    public BillTicket delete(int id) {
        var current = billTicketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data not found"));
        billTicketRepository.delete(current);
        return current;
    }
}
