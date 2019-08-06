package intiveFDV.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Override
	public List<Bike> givmeBikes(int size) {
		Optional<List<Bike>> bikes= bikeRepository.findAvalaible();
		if(bikes.isPresent() && bikes.get().size() >= size){
			List<Bike> selectedForRented = bikes.stream()
					.flatMap(List::stream)
        			.limit(size)
        			.map(b -> new Bike(b.getId(), BikeStatus.RENDTED))
        			.collect(Collectors.toList());
        bikeRepository.saveAll(selectedForRented);
        return	selectedForRented;		
		}else {
			throw new NotEnoughBikesException();
		}
	}

}
