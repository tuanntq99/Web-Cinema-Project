package WebCinema.Services.Interfaces;

import WebCinema.Entity.RankCustomer;

public interface IRankCustomerService {
    RankCustomer addNew(RankCustomer newRequest);

    RankCustomer remake(RankCustomer remakeRequest);

    RankCustomer delete(String name);
}
