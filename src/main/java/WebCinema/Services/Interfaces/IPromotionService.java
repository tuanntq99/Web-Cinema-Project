package WebCinema.Services.Interfaces;

import WebCinema.Entity.Promotion;


public interface IPromotionService {
    Promotion addNew(Promotion newPromotion);

    Promotion remake(Promotion remakePromotion);

    Promotion delete(String type);
}
