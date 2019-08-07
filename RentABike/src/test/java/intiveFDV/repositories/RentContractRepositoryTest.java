package intiveFDV.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import intiveFDV.domain.Bike;
import intiveFDV.domain.BikeStatus;
import intiveFDV.domain.Promotion;
import intiveFDV.domain.PromotionType;
import intiveFDV.domain.RentContract;
import intiveFDV.domain.RentItem;
import intiveFDV.domain.RentType;
import intiveFDV.domain.TimeUnit;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RentContractRepositoryTest {
	
	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private RentContractRepository RentContractRepository;
    
    
    List<RentItem> rentItems;
    Promotion promotion;
    Bike bike;
    RentType rentType;
    RentItem rentItem;
	
	@Before
	public void setUp() {
		bike = new Bike(BikeStatus.AVAILABLE);
		rentType = new RentType(TimeUnit.DAY, 12.0D);
		rentItem = new RentItem(bike, rentType, 3);
		rentItems = Arrays.asList(rentItem );
		promotion = new Promotion(PromotionType.FAMILY_RENT, 0.3D);
		entityManager.persistAndFlush(rentType);
		entityManager.persistAndFlush(bike);
		entityManager.persistAndFlush(rentItem);
		entityManager.persistAndFlush(promotion);
	}
    

    @Test
    public void whenFindByDay_thenReturnRentContract() {
    	// given
    	RentContract rentContract = new RentContract("0AD", rentItems, promotion);
        entityManager.persist(rentContract);
        entityManager.flush();
     
        // when
        List<RentContract> found = RentContractRepository.findByUserId(rentContract.getUserId());
     
        // then
        assertThat(found.get(0).getUserId()).isEqualTo(rentContract.getUserId());
    }
    
    @Test
    public void whenFindAll_thenReturnRentContractList() {
    	// given
    	RentContract rentContract1 = new RentContract("0AD", rentItems, promotion);
    	RentContract rentContract2 = new RentContract("1AD", rentItems, promotion);
    	List<RentContract> listrentContract = Arrays.asList(rentContract1, rentContract2);
        entityManager.persist(rentContract1);
        entityManager.persist(rentContract2);
        entityManager.flush();
     
        // when
        List<RentContract> found = RentContractRepository.findAll();
     
        // then
        assertThat(found.size()).isEqualTo(listrentContract.size());
    }
    
    @Test
    public void whenSave_thenRentContractExist() {
    	// given
    	RentContract rentContract1 = new RentContract("0AD", rentItems, promotion);
        // when
        RentContractRepository.save(rentContract1);
        RentContract found = entityManager.find(RentContract.class, rentContract1.getId());
        // then
        assertThat(found.getId()).isEqualTo(rentContract1.getId());
        
    }
    
    
    
}
