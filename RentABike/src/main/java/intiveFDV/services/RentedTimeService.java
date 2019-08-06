package intiveFDV.services;

import java.util.List;

import intiveFDV.domain.RentType;
import intiveFDV.dto.RentedTimeRequestDto;

public interface RentedTimeService {

	List<RentType> getRentedTime(List<RentedTimeRequestDto> rentedTime);

}
