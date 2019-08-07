package intiveFDV.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import intiveFDV.domain.Promotion;
import intiveFDV.domain.PromotionType;
import intiveFDV.repositories.PromotionRepository;

@Service
public class PromotionServiceImpl implements PromotionService {

	@Autowired
	private PromotionRepository promotionRepository;
	
	@Override
	public Promotion getPromotion(PromotionType promotionType) {
		return promotionRepository.findByType(promotionType);
	}

}
