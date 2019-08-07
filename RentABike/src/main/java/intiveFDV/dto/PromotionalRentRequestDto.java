package intiveFDV.dto;

import java.util.List;

import org.springframework.lang.Nullable;

import intiveFDV.domain.PromotionType;

public class PromotionalRentRequestDto extends RentRequestDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PromotionType promotionType;

	public PromotionType getPromotionType() {
		return promotionType;
	}

	public void setPromotionType(PromotionType promotionType) {
		this.promotionType = promotionType;
	}

	public PromotionalRentRequestDto(String userId, List<RentedTimeRequestDto> rentedTimeRequestDto, PromotionType promotionType) {
		super(userId, rentedTimeRequestDto);
		this.promotionType = promotionType;
	}


	public static PromotionalRentRequestDto castToPromotionalRentRequestDto(RentRequestDto request) {
		return new PromotionalRentRequestDto(request.getUserId(), request.getRentedTime(), null);
	}
	
	
}
