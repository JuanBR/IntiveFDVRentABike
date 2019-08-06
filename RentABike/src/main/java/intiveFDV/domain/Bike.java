package intiveFDV.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Bike")
public class Bike {
	private Long id;
	private BikeStatus status;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BikeStatus getStatus() {
		return status;
	}
	public void setStatus(BikeStatus status) {
		this.status = status;
	}
	public Bike(Long id, BikeStatus status) {
		super();
		this.id = id;
		this.status = status;
	}
	
	public Bike(BikeStatus status) {
		super();
		this.status = status;
	}
	public Bike() {
		super();
	}
	
	
	
	
}
