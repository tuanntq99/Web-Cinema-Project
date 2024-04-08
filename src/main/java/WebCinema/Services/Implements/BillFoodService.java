package WebCinema.Services.Implements;

import WebCinema.Config.ApplicationConfig;
import WebCinema.Entity.BillFood;
import WebCinema.Repository.BillFoodRepository;
import WebCinema.Services.Interfaces.IBillFoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BillFoodService implements IBillFoodService {
    private final BillFoodRepository billFoodRepository;
    private final ApplicationConfig config;

    @Override
    public BillFood addNew(BillFood newBillFood) {
        return billFoodRepository.save(newBillFood);
    }

    @Override
    public BillFood remake(BillFood remakeBilLFood) {
        var current = billFoodRepository.findById(remakeBilLFood.getId())
                .orElseThrow(() -> new RuntimeException("Data not found"));
        BeanUtils.copyProperties(remakeBilLFood, current, config.getNullPropertyNames(remakeBilLFood));
        return billFoodRepository.save(current);
    }

    @Override
    public BillFood delete(int id) {
        var current = billFoodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data not found"));
        billFoodRepository.delete(current);
        return current;
    }
}
