package intiveFDV.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import intiveFDV.domain.Bike;
import intiveFDV.domain.RentItem;
import intiveFDV.domain.RentType;
import intiveFDV.dto.PromotionalRentRequestDto;
import intiveFDV.exception.NotEnoughBikesException;
import intiveFDV.listener.RentContractMessageListener;
import intiveFDV.repositories.RentContractRepository;
import intiveFDV.repositories.RentItemRepository;
import net.bytebuddy.implementation.bytecode.Throw;

@Service
public class RentItemServiceImpl implements RentItemService {
	
	@Autowired
	BikeService bikeService; 
	@Autowired
	RentedTimeService rentedTimeService;
	@Autowired
	RentItemRepository rentItemRepository; 
	
    private static final Logger log = LogManager.getLogger(RentItemServiceImpl.class);

	
	@Override
	public List<RentItem> createRentItems(PromotionalRentRequestDto rentRequest) {
		try{
			return tryToCreateRentItems(rentRequest);
		}catch (NotEnoughBikesException e) {
			log.error("Not Enough Bikes ");
			throw e;
		}catch (Exception e) {
			log.error("error trying to create rentItems ");
			throw e;
		}
		
	}

	private List<RentItem> tryToCreateRentItems(PromotionalRentRequestDto rentRequest) {
		List<RentItem> list = new ArrayList<RentItem>();
		List<Bike> bikesAvailabeForRent = bikeService.givmeBikes(rentRequest.getRentedTime().size());
		List<RentType> rentTypes = rentedTimeService.getRentedTime(rentRequest.getRentedTime());
		for (int i = 0; i < rentRequest.getRentedTime().size(); i++) {
			RentItem rentItem = new RentItem(bikesAvailabeForRent.get(i), rentTypes.get(i), rentRequest.getRentedTime().get(i).getUnit());
			list.add(rentItem);
		}
		rentItemRepository.saveAll(list);
		return list;
	}



}
