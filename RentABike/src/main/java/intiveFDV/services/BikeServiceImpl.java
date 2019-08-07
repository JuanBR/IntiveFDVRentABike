package intiveFDV.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import intiveFDV.domain.Bike;
import intiveFDV.domain.BikeStatus;
import intiveFDV.exception.NotEnoughBikesException;
import intiveFDV.repositories.BikeRepository;
import intiveFDV.repositories.RentContractRepository;

@Service
public class BikeServiceImpl implements BikeService {
	
	@Autowired
	private BikeRepository bikeRepository;
	
    private static final Logger log = LogManager.getLogger(BikeServiceImpl.class);

	
	@Override
	public List<Bike> givmeBikes(int size) {
		Pageable pageable = PageRequest.of(0,size);
		Optional<List<Bike>> bikes= bikeRepository.findAvailable(size, pageable);
		if(bikes.isPresent() && bikes.get().size() >= size){
			List<Bike> selectedForRented = bikes.stream()
					.flatMap(List::stream)
        			.limit(size)
        			.map(b -> new Bike(b.getId(), BikeStatus.RENDTED))
        			.collect(Collectors.toList());
        bikeRepository.saveAll(selectedForRented);
        return	selectedForRented;		
		}else {
			log.warn("Not Enough Bikes availabe ");
			throw new NotEnoughBikesException();
		}
	}

}
