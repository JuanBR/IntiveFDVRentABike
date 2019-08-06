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
import intiveFDV.domain.Promocion;
import intiveFDV.domain.PromocionType;
import intiveFDV.domain.RentItem;
import intiveFDV.domain.RentType;
import intiveFDV.domain.TimeUnit;
import intiveFDV.dto.PromocionalRentRequestDto;
import intiveFDV.dto.RentedTimeRequestDto;

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
	
	  List<RentItem> rentItems;
	  Promocion promocion;
	  Bike bike;
	  List<Bike> bikes;
	  RentType rentType;
	  List<RentType> rentTypes;
	  RentItem rentItem;
	  PromocionalRentRequestDto rentRequest;
		
	@Before
	public void setUp() {
		bike = new Bike(BikeStatus.AVALAIBLE);
		bikes = Arrays.asList(bike);
		rentType = new RentType(TimeUnit.DAY, 12.0D);
		rentTypes = Arrays.asList(rentType);
		rentItem = new RentItem(bike, rentType, 3);
		rentItems = Arrays.asList(rentItem );
		promocion = new Promocion(PromocionType.FAMILY_RENT, 0.3D);
		rentRequest = new PromocionalRentRequestDto("user", Arrays.asList(new RentedTimeRequestDto(TimeUnit.DAY, 3)), 
				PromocionType.FAMILY_RENT);
	}
	
	
	@Test
	public void createRentItems(){
		given(bikeService.givmeBikes(Mockito.anyInt())).willReturn(bikes);
		given(rentedTimeService.getRentedTime(Mockito.<RentedTimeRequestDto>anyList())).willReturn(rentTypes);
		
		assertThat(rentItemService.createRentItems(rentRequest).size()).isEqualTo(rentItems.size());
	}
	
}
