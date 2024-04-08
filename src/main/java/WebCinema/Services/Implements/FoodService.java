package WebCinema.Services.Implements;

import WebCinema.Repository.FoodRepository;
import WebCinema.Config.ApplicationConfig;
import WebCinema.Entity.Food;
import WebCinema.Payloads.DataResponses.DataFood.SortFoodByQuantity;
import WebCinema.Repository.BillFoodRepository;
import WebCinema.Services.Interfaces.IFoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class FoodService implements IFoodService {
    private final FoodRepository foodRepository;
    private final BillFoodRepository billFoodRepository;
    private final ApplicationConfig config;

    @Override
    public Food addNew(Food newFood) {
        return foodRepository.save(newFood);
    }

    @Override
    public Food remake(Food remakeFood) {
        var current = foodRepository.findById(remakeFood.getId())
                .orElseThrow(() -> new RuntimeException("Data not found !"));
        BeanUtils.copyProperties(remakeFood, current, config.getNullPropertyNames(remakeFood));
        return foodRepository.save(current);
    }

    @Override
    public Food delete(String name) {
        var current = foodRepository.findByNameOfFood(name)
                .orElseThrow(() -> new RuntimeException("Data not found !"));
        billFoodRepository.findAll().forEach(x -> {
            if (x.getFood().getNameOfFood().equals(name)) x.setFood(null);
        });
        foodRepository.delete(current);
        return current;
    }

    @Override
    public List<SortFoodByQuantity> sortFoodByQuantity() {
        // Get the current time in milliseconds
        long currentTimeMillis = System.currentTimeMillis();

        // Subtract 7 days (7 * 24 * 60 * 60 * 1000 milliseconds) from the current time
        long sevenDaysAgoMillis = currentTimeMillis - (7L * 24L * 60L * 60L * 1000L);

        // Create a new Date object representing the date 7 days ago
        Date sevenDaysAgo = new Date(sevenDaysAgoMillis);

//        List<SortFoodByQuantity> obj = new ArrayList<>();
//        billFoodRepository.findAll().forEach(x -> {
//            var id = x.getId();
//            var name = x.getFood().getNameOfFood();
//            int quantity = x.getQuantity();
//            var date = x.getBills().getUpdateTime();
//            SortFoodByQuantity existingItem = null;
//            for (SortFoodByQuantity item : obj) {
//                if (item.getName().equals(name)) {
//                    existingItem = item;
//                    break;
//                }
//            }
//            if (date.compareTo(sevenDaysAgo) >= 0) {
//                if (existingItem != null) {
//                    // Nếu tên thức ăn đã tồn tại trong danh sách obj, cập nhật số lượng của nó
//                    existingItem.setQuantity(existingItem.getQuantity() + quantity);
//                } else {
//                    // Nếu chưa tồn tại, thêm mới vào danh sách obj
//                    SortFoodByQuantity newList = new SortFoodByQuantity(id, name, quantity);
//                    obj.add(newList);
//                }
//            }
//        });

        Map<String, SortFoodByQuantity> foodMap = new HashMap<>();

        billFoodRepository.findAll().forEach(x -> {
            var name = x.getFood().getNameOfFood();
            int quantity = x.getQuantity();
            var date = x.getBills().getUpdateTime();
            if (date.compareTo(sevenDaysAgo) >= 0) {
                foodMap.compute(name, (key, existingItem) -> {
                    if (existingItem != null) {
                        // Nếu mục đã tồn tại, cập nhật số lượng
                        existingItem.setQuantity(existingItem.getQuantity() + quantity);
                        return existingItem;
                    } else {
                        // Nếu mục chưa tồn tại, thêm mới vào Map
                        return new SortFoodByQuantity(x.getId(), name, quantity);
                    }
                });
            }
        });

        // Lưu các giá trị từ Map vào danh sách obj
        List<SortFoodByQuantity> obj = new ArrayList<>(foodMap.values());

        return obj.stream()
                .sorted(Comparator.comparingInt(SortFoodByQuantity::getQuantity).reversed())
                .toList();
    }

}
