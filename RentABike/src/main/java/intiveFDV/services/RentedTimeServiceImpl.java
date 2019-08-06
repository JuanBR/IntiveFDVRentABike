package intiveFDV.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import intiveFDV.domain.RentType;
import intiveFDV.dto.RentedTimeRequestDto;
import intiveFDV.repositories.RentTypeRepository;

@Service
public class RentedTimeServiceImpl implements RentedTimeService {
	
	@Autowired
	private RentTypeRepository rentedTypeRepository;

	@Override
	public List<RentType> getRentedTime(List<RentedTimeRequestDto> rentedTime) {
			return rentedTime.stream()
					.map(rTime -> {
						return rentedTypeRepository.findByTimeUnit(rTime.getTimeUnit());
					})
					.collect(Collectors.toList());
				
	}

}
