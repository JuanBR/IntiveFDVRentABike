package intiveFDV.listener;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import intiveFDV.domain.PromotionType;
import intiveFDV.dto.PromotionalRentRequestDto;
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
	
	
	PromotionalRentRequestDto rentRequest;
		
	@Before
	public void setUp() {
		rentRequest = new PromotionalRentRequestDto("user", Arrays.asList(RentedTimeRequestDto.rentedForDay(3)), 
				PromotionType.FAMILY_RENT);
	}
	
	@Test
	 public void receiveMessage() {
		 Map<String, PromotionalRentRequestDto> message = new HashMap<String, PromotionalRentRequestDto>();
		 message.put("promotionalRentRequest", rentRequest);
		 
		 Mockito.doNothing().when(contractService).createARentContract(Mockito.any(PromotionalRentRequestDto.class));
		 rentContractMessageListener.receiveMessage(message);
		 Mockito.verify(contractService, Mockito.times(1)).createARentContract(Mockito.any(PromotionalRentRequestDto.class));
	  	} 
	
}
