package intiveFDV.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import intiveFDV.domain.Promotion;
import intiveFDV.domain.PromotionType;

public interface PromotionRepository extends JpaRepository<Promotion, Long>  {
	
	Promotion findByType(PromotionType promotionType);

}
