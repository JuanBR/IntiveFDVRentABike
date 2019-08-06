package intiveFDV.listener;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import intiveFDV.dto.PromocionalRentRequestDto;
import intiveFDV.services.ContractService;

@Component
public class RentContractMessageListener {
	
	@Autowired
	private ContractService contractService;

	

    private static final Logger log = LogManager.getLogger(RentContractMessageListener.class);

    @Transactional
    public void receiveMessage(Map<String, PromocionalRentRequestDto> message) {
    	log.info("message recived ");
        log.debug("Received <" + message + ">");
        PromocionalRentRequestDto rentRequest = message.get("promocionalRentRequest");
        contractService.createARentContract(rentRequest);
    } 
        
        
        
    
    
}
