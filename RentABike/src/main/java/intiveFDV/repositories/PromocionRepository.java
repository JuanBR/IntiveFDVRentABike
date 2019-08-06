package intiveFDV.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import intiveFDV.domain.Promocion;
import intiveFDV.domain.PromocionType;

public interface PromocionRepository extends JpaRepository<Promocion, Long>  {
	
	Promocion findByType(PromocionType promocionType);

}
