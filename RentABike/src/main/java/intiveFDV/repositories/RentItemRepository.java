package intiveFDV.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import intiveFDV.domain.RentItem;
import intiveFDV.domain.RentType;
import intiveFDV.domain.TimeUnit;

public interface RentItemRepository extends JpaRepository<RentItem, Long> {
}
