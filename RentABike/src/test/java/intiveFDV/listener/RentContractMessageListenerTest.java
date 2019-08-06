package intiveFDV.listener;

import static org.assertj.core.api.Assertions.anyOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import intiveFDV.services.ContractService;

@RunWith(SpringRunner.class)
public class RentContractMessageListenerTest {

	
	@TestConfiguration
	static class RentContractMessageListenerTestContextConfiguration {
		@Bean
	    public RentContractMessageListener RentContractMessageListener() {
			return new RentContractMessageListener();
	    }
	}
	
	@Autowired
	RentContractMessageListener rentContractMessageListener;
	@MockBean
	ContractService contractService;
	
	
	PromocionalRentRequestDto rentRequest;
		
	@Before
	public void setUp() {
		rentRequest = new PromocionalRentRequestDto("user", Arrays.asList(new RentedTimeRequestDto(TimeUnit.DAY, 3)), 
				PromocionType.FAMILY_RENT);
	}
	
	@Test
	 public void receiveMessage() {
		 Map<String, PromocionalRentRequestDto> message = new HashMap<>();
		 message.put("promocionalRentRequestDto", rentRequest);
		 
		 Mockito.doNothing().when(contractService).createARentContract(Mockito.any(PromocionalRentRequestDto.class));
		 rentContractMessageListener.receiveMessage(message);
		 Mockito.verify(contractService, Mockito.times(1)).createARentContract(Mockito.any(PromocionalRentRequestDto.class));
	  	} 
	
}
