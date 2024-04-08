package WebCinema.Payloads.DataResponses.DataFood;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SortFoodByQuantity {
    int id;
    String name;
    int quantity;
}
