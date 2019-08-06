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
import intiveFDV.domain.Promocion;
import intiveFDV.domain.PromocionType;
import intiveFDV.domain.RentContract;
import intiveFDV.domain.RentItem;
import intiveFDV.domain.RentType;
import intiveFDV.domain.TimeUnit;
import intiveFDV.dto.PromocionalRentRequestDto;
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
	 Promocion promocion;
	 RentItem rentItem;
	 PromocionalRentRequestDto rentRequest;
	 List<RentContract> contracts;
	 Bike bike;
	 RentType rentType;
	 RentRequestDto rentRequestDto;
		
	@Before
	public void setUp() {
		bike = new Bike(BikeStatus.AVALAIBLE);
		rentType = new RentType(TimeUnit.DAY, 12.0D);
		rentItem = new RentItem(bike, rentType, 3);
		rentItems = Arrays.asList(rentItem );
		promocion = new Promocion(PromocionType.FAMILY_RENT, 0.3D);
		rentRequest = new PromocionalRentRequestDto("user", Arrays.asList(new RentedTimeRequestDto(TimeUnit.DAY, 3)), 
				PromocionType.FAMILY_RENT);
		
		contracts = Arrays.asList(new RentContract("user", rentItems, promocion)) ;
		rentRequestDto = new RentRequestDto("user", Arrays.asList(new RentedTimeRequestDto(TimeUnit.DAY, 3)));
	}

	@Test
	public void getAllRentContractsByUser() {
		given(rentContractRepository.findByUserId(Mockito.anyString())).willReturn(contracts);
		assertThat(rentABikeService.getAllRentContractsByUser("user").get(0).getUserId()).
									isEqualTo(contracts.get(0).getUserId());
	}
	
	@Test
	public void rentABikeWithAPromocion() {
		Mockito.doNothing().when(rabbitTemplate).convertAndSend(Mockito.anyString(), Mockito.anyMap());
		rentABikeService.rentABikeWithAPromocion(rentRequest);
		Mockito.verify(rabbitTemplate, Mockito.times(1)).convertAndSend(Mockito.anyString(), Mockito.anyMap());
		
	}
	
	@Test
	public void rentABike() {
		Mockito.doNothing().when(rabbitTemplate).convertAndSend(Mockito.anyString(), Mockito.anyMap());
		rentABikeService.rentABike(rentRequestDto);
		Mockito.verify(rabbitTemplate, Mockito.times(1)).convertAndSend(Mockito.anyString(), Mockito.anyMap());
	}


	
	
}
