package WebCinema.Repository;

import WebCinema.Entity.Bill;
import WebCinema.Entity.BillFood;
import WebCinema.Entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillFoodRepository extends JpaRepository<BillFood, Integer> {
    Optional<BillFood> findBillFoodByBillsAndFood(Bill bill, Food food);

}
