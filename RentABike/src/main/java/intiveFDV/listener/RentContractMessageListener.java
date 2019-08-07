package intiveFDV.listener;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import intiveFDV.dto.PromotionalRentRequestDto;
import intiveFDV.services.ContractService;

@Component
public class RentContractMessageListener {
	
	@Autowired
	private ContractService contractService;

	

    private static final Logger log = LogManager.getLogger(RentContractMessageListener.class);

    @Transactional
    public void receiveMessage(Map<String, PromotionalRentRequestDto> message) {
    	log.info("message recived ");
        log.debug("Received <" + message + ">");
        PromotionalRentRequestDto rentRequest = message.get("promotionalRentRequest");
        contractService.createARentContract(rentRequest);
    } 
        
        
        
    
    
}
