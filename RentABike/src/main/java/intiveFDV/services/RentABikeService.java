package intiveFDV.services;

import java.util.List;

import intiveFDV.domain.RentContract;
import intiveFDV.dto.PromocionalRentRequestDto;
import intiveFDV.dto.RentRequestDto;

public interface RentABikeService {

	List<RentContract> getAllRentContractsByUser(String userName);

	void rentABike(RentRequestDto request);

	void rentABikeWithAPromocion(PromocionalRentRequestDto request);

}
