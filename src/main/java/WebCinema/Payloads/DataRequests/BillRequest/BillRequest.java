package WebCinema.Payloads.DataRequests.BillRequest;

import WebCinema.Entity.BillStatus;
import WebCinema.Entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BillRequest {
    int id;
    double totalMoney;
    String tradingCode;
    Date createTime;
    String name;
    Date updateTime;
    User users;
    int promotion;
    BillStatus billStatus;

    List<FoodQuantityPair> foodItems;

    List<TicketQuantityPair> ticketItems;

    @Getter
    @Setter
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class FoodQuantityPair {
        int quantity;
        int food;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class TicketQuantityPair {
        int quantity;
        int ticket;
    }

}
