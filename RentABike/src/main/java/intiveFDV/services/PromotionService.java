package intiveFDV.services;

import intiveFDV.domain.Promotion;
import intiveFDV.domain.PromotionType;

public interface PromotionService {

	Promotion getPromotion(PromotionType promotionType);

}
