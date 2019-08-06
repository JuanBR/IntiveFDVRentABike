package intiveFDV.dto;

import java.util.List;

import org.springframework.lang.Nullable;

import intiveFDV.domain.PromocionType;

public class PromocionalRentRequestDto extends RentRequestDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PromocionType promocionType;

	public PromocionType getPromocionType() {
		return promocionType;
	}

	public void setPromocionType(PromocionType promocionType) {
		this.promocionType = promocionType;
	}

	public PromocionalRentRequestDto(String userId, List<RentedTimeRequestDto> rentedTimeRequestDto, PromocionType promocionType) {
		super(userId, rentedTimeRequestDto);
		this.promocionType = promocionType;
	}

	public static PromocionalRentRequestDto castToPromocionalRentRequestDto(RentRequestDto request) {
		return new PromocionalRentRequestDto(request.getUserId(), request.getRentedTime(), null);
	}
	
	
}
