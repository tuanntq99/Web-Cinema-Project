package WebCinema.Services.Interfaces;

import WebCinema.Entity.Food;
import WebCinema.Payloads.DataResponses.DataFood.SortFoodByQuantity;

import java.util.List;

public interface IFoodService {
    Food addNew(Food newFood);

    Food remake(Food remakeFood);

    Food delete(String name);

    List<SortFoodByQuantity> sortFoodByQuantity();
}
