package intiveFDV.dto;

import java.io.Serializable;
import java.util.List;

import intiveFDV.domain.PromotionType;
import intiveFDV.domain.TimeUnit;

public class RentRequestDto  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	private List<RentedTimeRequestDto> rentedTime;

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<RentedTimeRequestDto> getRentedTime() {
		return rentedTime;
	}
	public void setRentedTime(List<RentedTimeRequestDto> rentedTime) {
		this.rentedTime = rentedTime;
	}
	public RentRequestDto(String userId, List<RentedTimeRequestDto> rentedTime) {
		super();
		this.userId = userId;
		this.rentedTime = rentedTime;
	}
	
	
	
}
