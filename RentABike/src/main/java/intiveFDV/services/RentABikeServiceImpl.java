package intiveFDV.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import intiveFDV.RentABikeApplication;
import intiveFDV.domain.RentContract;
import intiveFDV.dto.PromotionalRentRequestDto;
import intiveFDV.dto.RentRequestDto;
import intiveFDV.repositories.RentContractRepository;

@Service
public class RentABikeServiceImpl implements RentABikeService {
	
	private static final Logger log = LogManager.getLogger(RentABikeServiceImpl.class);
	@Autowired
	private RentContractRepository rentContractRepository;
	@Autowired
	private RabbitTemplate rabbitTemplate;


	@Override
	public List<RentContract> getAllRentContractsByUser(String userId) {
		return rentContractRepository.findByUserId(userId);
	}
	
	
	private void sendRentContractMessage(PromotionalRentRequestDto promotionalRentRequest) {
		Map<String, PromotionalRentRequestDto> actionmap = new HashMap<>();
	    actionmap.put("promotionalRentRequest", promotionalRentRequest);
	    log.info("Sending the rent with promotion request through queue message");
	    rabbitTemplate.convertAndSend(RentABikeApplication.FDV_MESSAGE_QUEUE, actionmap);
	}
	
	private void sendRentContractMessage(RentRequestDto rentRequest) {
		Map<String, PromotionalRentRequestDto> actionmap = new HashMap<>();
		PromotionalRentRequestDto request = PromotionalRentRequestDto.castToPromotionalRentRequestDto(rentRequest) ;
	    actionmap.put("promotionalRentRequest", request);
	    log.info("Sending the rent request through queue message");
	    rabbitTemplate.convertAndSend(RentABikeApplication.FDV_MESSAGE_QUEUE, actionmap);
	}

	@Override
	public void rentABike(RentRequestDto request) {
		sendRentContractMessage(request);
	}


	@Override
	public void rentABikeWithAPromotion(PromotionalRentRequestDto request) {
		sendRentContractMessage(request);
	}

}
