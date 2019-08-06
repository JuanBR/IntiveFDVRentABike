package intiveFDV.dto;

import java.io.Serializable;
import java.util.List;

import intiveFDV.domain.TimeUnit;

public class RentedTimeRequestDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TimeUnit timeUnit;
	private Integer unit;
	
	public TimeUnit getTimeUnit() {
		return timeUnit;
	}
	public void setTimeUnit(TimeUnit timeUnit) {
		this.timeUnit = timeUnit;
	}
	public Integer getUnit() {
		return unit;
	}
	public void setUnit(Integer unit) {
		this.unit = unit;
	}
	
	public RentedTimeRequestDto(TimeUnit timeUnit, Integer unit) {
		super();
		this.timeUnit = timeUnit;
		this.unit = unit;
	}
	
	
	
}
