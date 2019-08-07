package intiveFDV.services;

import java.util.List;

import intiveFDV.domain.Bike;
import intiveFDV.domain.RentItem;
import intiveFDV.domain.RentType;
import intiveFDV.dto.PromotionalRentRequestDto;

public interface RentItemService {

	List<RentItem> createRentItems(PromotionalRentRequestDto rentRequest);

}
