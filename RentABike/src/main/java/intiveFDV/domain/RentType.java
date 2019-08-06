package intiveFDV.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RentType")
public class RentType {
 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private TimeUnit timeUnit;
	private Double chargePerTymeUnit;
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public TimeUnit getTimeUnit() {
		return timeUnit;
	}
	
	public Double getChargePerTymeUnit() {
		return chargePerTymeUnit;
	}

	public RentType(TimeUnit timeUnit, Double chargePerTymeUnit) {
		super();
		this.timeUnit = timeUnit;
		this.chargePerTymeUnit = chargePerTymeUnit;
	}

	public RentType() {
		super();
	}

	
	
	
}
