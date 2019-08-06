package intiveFDV.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import intiveFDV.domain.RentType;
import intiveFDV.domain.TimeUnit;


@RunWith(SpringRunner.class)
@DataJpaTest
public class RentTypeRepositoryTest {
	
	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private RentTypeRepository RentTypeRepository;
    
    // write test cases here
    

    @Test
    public void whenFindByDay_thenReturnWheatherRegister() {
    	// given
    	RentType rentType = new RentType(TimeUnit.DAY,0.3D);
        entityManager.persist(rentType);
        entityManager.flush();
     
        // when
       RentType found = RentTypeRepository.findByTimeUnit(rentType.getTimeUnit());
     
        // then
        assertThat(found.getChargePerTymeUnit()).isEqualTo(rentType.getChargePerTymeUnit());
    }
    
    @Test
    public void whenFindAll_thenReturnRentTypeList() {
    	// given
    	RentType rentType1 = new RentType(TimeUnit.DAY,0.3D);
    	RentType rentType2 = new RentType(TimeUnit.DAY,0.3D);
    	List<RentType> listrentType = Arrays.asList(rentType1, rentType2);
        entityManager.persist(rentType1);
        entityManager.persist(rentType2);
        entityManager.flush();
     
        // when
        List<RentType> found = RentTypeRepository.findAll();
     
        // then
        assertThat(found.size()).isEqualTo(listrentType.size());
    }
    
    @Test
    public void whenSave_thenRentTypeExist() {
    	// given
    	RentType rentType1 = new RentType(TimeUnit.DAY,0.3D);
        // when
       RentTypeRepository.save(rentType1);
       RentType found = entityManager.find(RentType.class, rentType1.getId());
        // then
        assertThat(found.getId()).isEqualTo(rentType1.getId());
        
    }
    
    
    
}
