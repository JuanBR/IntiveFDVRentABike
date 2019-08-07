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

import intiveFDV.domain.Promotion;
import intiveFDV.domain.PromotionType;



@RunWith(SpringRunner.class)
@DataJpaTest
public class PromotionRepositoryTest {
	
	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private PromotionRepository PromotionRepository;
    
    // write test cases here
    

    @Test
    public void whenFindByDay_thenReturnPromotion() {
    	// given
    	Promotion promotion = new Promotion(PromotionType.FAMILY_RENT, 0.3D);
        entityManager.persist(promotion);
        entityManager.flush();
     
        // when
        Promotion found = PromotionRepository.findByType(PromotionType.FAMILY_RENT);
     
        // then
        assertThat(found.getId()).isEqualTo(promotion.getId());
    }
    
    @Test
    public void whenFindAll_thenReturnPromotionList() {
    	// given
    	Promotion promotion1 = new Promotion(PromotionType.FAMILY_RENT, 0.3D);
    	Promotion promotion2 = new Promotion(PromotionType.FAMILY_RENT, 0.2D);
    	List<Promotion> listpromotion = Arrays.asList(promotion1, promotion2);
        entityManager.persist(promotion1);
        entityManager.persist(promotion2);
        entityManager.flush();
     
        // when
        List<Promotion> found = PromotionRepository.findAll();
     
        // then
        assertThat(found.size()).isEqualTo(listpromotion.size());
    }
    
    @Test
    public void whenSave_thenPromotionExist() {
    	// given
    	Promotion promotion1 = new Promotion(PromotionType.FAMILY_RENT, 0.3D);
        // when
        PromotionRepository.save(promotion1);
        Promotion found = entityManager.find(Promotion.class, promotion1.getId());
        // then
        assertThat(found.getId()).isEqualTo(promotion1.getId());
        
    }
    
    
    
}
