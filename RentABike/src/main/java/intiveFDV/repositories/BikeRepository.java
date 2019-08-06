package intiveFDV.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import intiveFDV.domain.Bike;

public interface BikeRepository extends JpaRepository<Bike, Long> {

	@Query("SELECT b FROM Bike b where b.status = 1 ")
	Optional<List<Bike>> findAvalaible();

}
