package intiveFDV.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
import intiveFDV.dto.RentRequestDto;
import intiveFDV.dto.RentedTimeRequestDto;
import intiveFDV.repositories.RentContractRepository;

@RunWith(SpringRunner.class)
public class RentABikeServiceImplTest {

	
	@TestConfiguration
	static class RentABikeServiceImplTestContextConfiguration {
		@Bean
	    public RentABikeService RentABikeService() {
			return new RentABikeServiceImpl();
	    }
	}
	
	@Autowired
	private RentABikeService rentABikeService;
	
	@MockBean
	RentContractRepository rentContractRepository;
	@MockBean
	RabbitTemplate rabbitTemplate;
	
	 List<RentItem> rentItems;
	 Promotion promotion;
	 RentItem rentItem;
	 PromotionalRentRequestDto rentRequest;
	 List<RentContract> contracts;
	 Bike bike;
	 RentType rentType;
	 RentRequestDto rentRequestDto;
		
	@Before
	public void setUp() {
		bike = new Bike(BikeStatus.AVAILABLE);
		rentType = new RentType(TimeUnit.DAY, 12.0D);
		rentItem = new RentItem(bike, rentType, 3);
		rentItems = Arrays.asList(rentItem );
		promotion = new Promotion(PromotionType.FAMILY_RENT, 0.3D);
		rentRequest = new PromotionalRentRequestDto("user", Arrays.asList(new RentedTimeRequestDto(TimeUnit.DAY, 3)), 
				PromotionType.FAMILY_RENT);
		
		contracts = Arrays.asList(new RentContract("user", rentItems, promotion)) ;
		rentRequestDto = new RentRequestDto("user", Arrays.asList(new RentedTimeRequestDto(TimeUnit.DAY, 3)));
	}

	@Test
	public void getAllRentContractsByUser() {
		given(rentContractRepository.findByUserId(Mockito.anyString())).willReturn(contracts);
		assertThat(rentABikeService.getAllRentContractsByUser("user").get(0).getUserId()).
									isEqualTo(contracts.get(0).getUserId());
	}
	
	@Test
	public void rentABikeWithAPromotion() {
		Mockito.doNothing().when(rabbitTemplate).convertAndSend(Mockito.anyString(), Mockito.anyMap());
		rentABikeService.rentABikeWithAPromotion(rentRequest);
		Mockito.verify(rabbitTemplate, Mockito.times(1)).convertAndSend(Mockito.anyString(), Mockito.anyMap());
		
	}
	
	@Test
	public void rentABike() {
		Mockito.doNothing().when(rabbitTemplate).convertAndSend(Mockito.anyString(), Mockito.anyMap());
		rentABikeService.rentABike(rentRequestDto);
		Mockito.verify(rabbitTemplate, Mockito.times(1)).convertAndSend(Mockito.anyString(), Mockito.anyMap());
	}


	
	
}
