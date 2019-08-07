package intiveFDV.repositories;

import java.util.List;
import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import intiveFDV.domain.Bike;

public interface BikeRepository extends JpaRepository<Bike, Long> {

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("SELECT b FROM Bike b where b.status = 1 ")
	Optional<List<Bike>> findAvailable(Integer limit, Pageable pageable);

}
