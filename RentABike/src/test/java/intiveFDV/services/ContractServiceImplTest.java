package intiveFDV.services;




import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito.BDDMyOngoingStubbing;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import intiveFDV.domain.Bike;
import intiveFDV.domain.BikeStatus;
import intiveFDV.domain.Promotion;
import intiveFDV.domain.PromotionType;
import intiveFDV.domain.RentContract;
import intiveFDV.domain.RentItem;
import intiveFDV.domain.RentType;
import intiveFDV.domain.TimeUnit;
import intiveFDV.dto.PromotionalRentRequestDto;
import intiveFDV.dto.RentedTimeRequestDto;
import intiveFDV.repositories.RentContractRepository;

@RunWith(SpringRunner.class)
public class ContractServiceImplTest {
	
	@TestConfiguration
	static class ContractServiceImplTestContextConfiguration {
		@Bean
	    public ContractService ContractService() {
			return new ContractServiceImpl();
	    }
	}
	
	@Autowired
	ContractService contractService;
	
	@MockBean
	RentItemService rentItemService;
	@MockBean
	PromotionService promotionService;
	@MockBean
	RentContractRepository rentContractRepository; 

	  List<RentItem> rentItems;
	  Promotion promotion;
	  Bike bike;
	  RentType rentType;
	  RentItem rentItem;
	  PromotionalRentRequestDto rentRequest;
		
	@Before
	public void setUp() {
		bike = new Bike(BikeStatus.AVAILABLE);
		rentType = new RentType(TimeUnit.DAY, 12.0D);
		rentItem = new RentItem(bike, rentType, 3);
		rentItems = Arrays.asList(rentItem );
		promotion = new Promotion(PromotionType.FAMILY_RENT, 0.3D);
		rentRequest = new PromotionalRentRequestDto("user", Arrays.asList(new RentedTimeRequestDto(TimeUnit.DAY, 3)), 
				PromotionType.FAMILY_RENT);
	}
	
	@Test
	public void createARentContract() {
		//given
		given(rentItemService.createRentItems(Mockito.any(PromotionalRentRequestDto.class)))
		.willReturn(rentItems);
		given(promotionService.getPromotion(Mockito.any(PromotionType.class)))
		.willReturn(promotion);
		given(rentContractRepository.save(Mockito.any(RentContract.class)))
		.willReturn(new RentContract("user", rentItems, promotion));
		
		contractService.createARentContract(rentRequest);
		
		Mockito.verify(rentContractRepository, Mockito.times(1)).save(Mockito.any(RentContract.class));

	}
	
}
