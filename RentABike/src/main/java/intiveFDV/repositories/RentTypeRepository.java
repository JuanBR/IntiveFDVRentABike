package intiveFDV.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import intiveFDV.domain.RentType;
import intiveFDV.domain.TimeUnit;

public interface RentTypeRepository extends JpaRepository<RentType, Long> {
	RentType findByTimeUnit(TimeUnit timeUnit);
}
