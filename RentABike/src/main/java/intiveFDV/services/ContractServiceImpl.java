package intiveFDV.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import intiveFDV.domain.Promotion;
import intiveFDV.domain.RentContract;
import intiveFDV.domain.RentItem;
import intiveFDV.dto.PromotionalRentRequestDto;
import intiveFDV.exception.NotEnoughBikesException;
import intiveFDV.repositories.RentContractRepository;

@Service
public class ContractServiceImpl implements ContractService {
	
	@Autowired
	RentItemService rentItemService;
	@Autowired
	PromotionService promotionService;
	@Autowired
	RentContractRepository rentContractRepository; 
	
    private static final Logger log = LogManager.getLogger(ContractServiceImpl.class);

	
	@Override
	public void createARentContract(PromotionalRentRequestDto rentRequest) {
		try {
			tryToCreateARentContract(rentRequest);
		}catch (NotEnoughBikesException e) {
			log.warn("Not Enough Bikes ");
		}catch (Exception e) {
			log.error("error trying to create a contract ");
		}
		
	}

	private void tryToCreateARentContract(PromotionalRentRequestDto rentRequest) {
		List<RentItem> rentItems = rentItemService.createRentItems(rentRequest);
		Promotion promotion = promotionService.getPromotion(rentRequest.getPromotionType());
		RentContract contract = new RentContract(rentRequest.getUserId(), rentItems, promotion);
		rentContractRepository.save(contract);
		log.info("Contract close ");
	}

}
