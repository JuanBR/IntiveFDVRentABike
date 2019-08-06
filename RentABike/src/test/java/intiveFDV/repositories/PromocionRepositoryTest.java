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

import intiveFDV.domain.Promocion;
import intiveFDV.domain.PromocionType;



@RunWith(SpringRunner.class)
@DataJpaTest
public class PromocionRepositoryTest {
	
	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private PromocionRepository PromocionRepository;
    
    // write test cases here
    

    @Test
    public void whenFindByDay_thenReturnPromocion() {
    	// given
    	Promocion promocion = new Promocion(PromocionType.FAMILY_RENT, 0.3D);
        entityManager.persist(promocion);
        entityManager.flush();
     
        // when
        Promocion found = PromocionRepository.findByType(PromocionType.FAMILY_RENT);
     
        // then
        assertThat(found.getId()).isEqualTo(promocion.getId());
    }
    
    @Test
    public void whenFindAll_thenReturnPromocionList() {
    	// given
    	Promocion promocion1 = new Promocion(PromocionType.FAMILY_RENT, 0.3D);
    	Promocion promocion2 = new Promocion(PromocionType.FAMILY_RENT, 0.2D);
    	List<Promocion> listpromocion = Arrays.asList(promocion1, promocion2);
        entityManager.persist(promocion1);
        entityManager.persist(promocion2);
        entityManager.flush();
     
        // when
        List<Promocion> found = PromocionRepository.findAll();
     
        // then
        assertThat(found.size()).isEqualTo(listpromocion.size());
    }
    
    @Test
    public void whenSave_thenPromocionExist() {
    	// given
    	Promocion promocion1 = new Promocion(PromocionType.FAMILY_RENT, 0.3D);
        // when
        PromocionRepository.save(promocion1);
        Promocion found = entityManager.find(Promocion.class, promocion1.getId());
        // then
        assertThat(found.getId()).isEqualTo(promocion1.getId());
        
    }
    
    
    
}
