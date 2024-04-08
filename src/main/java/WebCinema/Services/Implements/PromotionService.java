package WebCinema.Services.Implements;

import WebCinema.Config.ApplicationConfig;
import WebCinema.Repository.PromotionRepository;
import WebCinema.Repository.RankCustomerRepository;
import WebCinema.Entity.Promotion;
import WebCinema.Repository.BillRepository;
import WebCinema.Services.Interfaces.IPromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PromotionService implements IPromotionService {
    private final RankCustomerRepository rankCustomerRepository;
    private final PromotionRepository promotionRepository;
    private final BillRepository billRepository;
    private final ApplicationConfig config;

    @Override
    public Promotion addNew(Promotion newPromotion) {
        promotionRepository.save(newPromotion);
        var current = promotionRepository.findByType(newPromotion.getType())
                .orElseThrow(() -> new RuntimeException("Data not found !"));
        rankCustomerRepository.findAll().forEach(rank -> rank.getPromotions().forEach(x -> {
            if (x.getRankCustomer().equals(current.getRankCustomer()) && !rank.isActive()) {
                promotionRepository.delete(current);
                newPromotion.setName("delete");
            }
        }));
        return newPromotion;
    }

    @Override
    public Promotion remake(Promotion remakePromotion) {
        var current = promotionRepository.findById((int) remakePromotion.getId())
                .orElseThrow(() -> new RuntimeException("Data not found !"));
        if (remakePromotion.getPercent() == 0) remakePromotion.setPercent(current.getPercent());
        if (remakePromotion.getQuantity() == 0) remakePromotion.setQuantity(current.getQuantity());
        BeanUtils.copyProperties(remakePromotion, current, config.getNullPropertyNames(remakePromotion));
        return promotionRepository.save(current);
    }

    @Override
    public Promotion delete(String type) {
        var current = promotionRepository.findByType(type)
                .orElseThrow(() -> new RuntimeException("Data not found !"));
        billRepository.findAll().forEach(x -> {
            if (x.getPromotions().equals(current)) x.setActive(false);
        });
        current.setActive(false);
        return promotionRepository.save(current);
    }
}
