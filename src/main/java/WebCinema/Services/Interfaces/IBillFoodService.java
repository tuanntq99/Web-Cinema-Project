package WebCinema.Services.Interfaces;

import WebCinema.Entity.BillFood;

public interface IBillFoodService {
    BillFood addNew(BillFood newBillFood);

    BillFood remake(BillFood remakeBilLFood);

    BillFood delete(int id);
}
