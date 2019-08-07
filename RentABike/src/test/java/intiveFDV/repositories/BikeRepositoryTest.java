package intiveFDV.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import intiveFDV.domain.Bike;
import intiveFDV.domain.BikeStatus;



@RunWith(SpringRunner.class)
@DataJpaTest
public class BikeRepositoryTest {
	
	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private BikeRepository bikeRepositoryRepository;
    
    // write test cases here
    

    @Test
    public void whenFindById_thenReturnBike() {
    	// given
    	Bike bike = new Bike(BikeStatus.AVAILABLE);
        entityManager.persist(bike);
        entityManager.flush();
     
        // when
        Optional<Bike> found = bikeRepositoryRepository.findById(bike.getId());
     
        // then
        assertThat(found.get().getId()).isEqualTo(bike.getId());
    }
    
    @Test
    public void whenFindAll_thenReturnBikeList() {
    	// given
    	Bike bike1 = new Bike(BikeStatus.AVAILABLE);
    	Bike bike2 = new Bike(BikeStatus.AVAILABLE);
    	List<Bike> listbike = Arrays.asList(bike1, bike2);
        entityManager.persist(bike1);
        entityManager.persist(bike2);
        entityManager.flush();
     
        // when
        List<Bike> found = bikeRepositoryRepository.findAll();
     
        // then
        assertThat(found.size()).isEqualTo(listbike.size());
    }
    
    @Test
    public void whenSave_thenBikeExist() {
    	// given
    	Bike bike1 = new Bike(BikeStatus.AVAILABLE);
        // when
        bikeRepositoryRepository.save(bike1);
        Bike found = entityManager.find(Bike.class, bike1.getId());
        // then
        assertThat(found.getId()).isEqualTo(bike1.getId());
        
    }
    
    
    
}
