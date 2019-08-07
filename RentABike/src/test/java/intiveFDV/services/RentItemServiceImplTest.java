package intiveFDV.services;

import static org.assertj.core.api.Assertions.anyOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.boot.model.source.spi.AnyDiscriminatorSource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;


import intiveFDV.domain.Bike;
import intiveFDV.domain.BikeStatus;
import intiveFDV.domain.Promotion;
import intiveFDV.domain.PromotionType;
import intiveFDV.domain.RentItem;
import intiveFDV.domain.RentType;
import intiveFDV.domain.TimeUnit;
import intiveFDV.dto.PromotionalRentRequestDto;
import intiveFDV.dto.RentedTimeRequestDto;
import intiveFDV.repositories.RentItemRepository;

@RunWith(SpringRunner.class)
public class RentItemServiceImplTest {

	
	@TestConfiguration
	static class RentItemServiceImplTestContextConfiguration {
		@Bean
	    public RentItemService RentItemService() {
			return new RentItemServiceImpl();
	    }
	}
	
	@Autowired
	private RentItemService rentItemService;
	
	@MockBean
	BikeService bikeService; 
	@MockBean
	RentedTimeService rentedTimeService;
	@MockBean
	RentItemRepository rentItemRepository; 
	
	  List<RentItem> rentItems;
	  Promotion promotion;
	  Bike bike;
	  List<Bike> bikes;
	  RentType rentType;
	  List<RentType> rentTypes;
	  RentItem rentItem;
	  PromotionalRentRequestDto rentRequest;
		
	@Before
	public void setUp() {
		bike = new Bike(BikeStatus.AVAILABLE);
		bikes = Arrays.asList(bike);
		rentType = new RentType(TimeUnit.DAY, 12.0D);
		rentTypes = Arrays.asList(rentType);
		rentItem = new RentItem(bike, rentType, 3);
		rentItems = Arrays.asList(rentItem );
		promotion = new Promotion(PromotionType.FAMILY_RENT, 0.3D);
		rentRequest = new PromotionalRentRequestDto("user", Arrays.asList(new RentedTimeRequestDto(TimeUnit.DAY, 3)), 
				PromotionType.FAMILY_RENT);
	}
	
	
	@Test
	public void createRentItems(){
		given(bikeService.givmeBikes(Mockito.anyInt())).willReturn(bikes);
		given(rentedTimeService.getRentedTime(Mockito.<RentedTimeRequestDto>anyList())).willReturn(rentTypes);
		given(rentItemRepository.saveAll(Mockito.<RentItem>anyList())).willReturn(rentItems);
		assertThat(rentItemService.createRentItems(rentRequest).size()).isEqualTo(rentItems.size());
	}
	
}
