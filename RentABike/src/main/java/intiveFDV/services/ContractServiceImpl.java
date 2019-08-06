package intiveFDV.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import intiveFDV.domain.Promocion;
import intiveFDV.domain.RentContract;
import intiveFDV.domain.RentItem;
import intiveFDV.dto.PromocionalRentRequestDto;
import intiveFDV.exception.NotEnoughBikesException;
import intiveFDV.repositories.RentContractRepository;

@Service
public class ContractServiceImpl implements ContractService {
	
	@Autowired
	RentItemService rentItemService;
	@Autowired
	PromocionService promocionService;
	@Autowired
	RentContractRepository rentContractRepository; 
	
    private static final Logger log = LogManager.getLogger(ContractServiceImpl.class);

	
	@Override
	public void createARentContract(PromocionalRentRequestDto rentRequest) {
		try {
			tryToCreateARentContract(rentRequest);
		}catch (NotEnoughBikesException e) {
			log.error("Not Enough Bikes ");
		}catch (Exception e) {
			log.error("error trying to create a contract ");
		}
		
	}

	private void tryToCreateARentContract(PromocionalRentRequestDto rentRequest) {
		List<RentItem> rentItems = rentItemService.createRentItems(rentRequest);
		Promocion promocion = promocionService.getPromocion(rentRequest.getPromocionType());
		RentContract contract = new RentContract(rentRequest.getUserId(), rentItems, promocion);
		rentContractRepository.save(contract);
		log.info("Contract close ");
	}

}
