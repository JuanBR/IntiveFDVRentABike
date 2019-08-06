package intiveFDV.services;

import static org.assertj.core.api.Assertions.anyOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
import intiveFDV.repositories.RentTypeRepository;

@RunWith(SpringRunner.class)
public class RentedTimeServiceImplTest {

	
	@TestConfiguration
	static class RentedTimeServiceImplTestContextConfiguration {
		@Bean
	    public RentedTimeService RentedTimeService() {
			return new RentedTimeServiceImpl();
	    }
	}
	
	@Autowired
	private RentedTimeService rentedTimeService;
	
	@MockBean
	private RentTypeRepository rentedTypeRepository;
	
	  RentType rentType;
	  List<RentedTimeRequestDto> rentedTime;
		
	@Before
	public void setUp() {
		rentType = new RentType(TimeUnit.DAY, 12.0D);
		rentedTime = Arrays.asList(new RentedTimeRequestDto(TimeUnit.DAY, 3));
	}

	@Test
	public void getRentedTime() {
		given(rentedTypeRepository.findByTimeUnit(Mockito.any(TimeUnit.class))).willReturn(rentType);
		assertThat(rentedTimeService.getRentedTime(rentedTime).get(0).getChargePerTymeUnit()).
		isEqualTo(rentType.getChargePerTymeUnit());
	}
	
	
	
}
