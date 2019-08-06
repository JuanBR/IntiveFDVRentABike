package intiveFDV.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import intiveFDV.domain.RentContract;

public interface RentContractRepository extends JpaRepository<RentContract, Long> {

	public List<RentContract> findByUserId(String userId);
}
