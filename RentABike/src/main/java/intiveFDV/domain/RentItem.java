package intiveFDV.domain;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RentItem")
public class RentItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@OneToOne(targetEntity=Bike.class)
	@JoinColumn(name="bikeId")
	private Bike bike;
	@OneToOne(targetEntity=RentType.class)
	@JoinColumn(name="rentedTymeId")
	private RentType rentedTyme;
	private Integer unitOfRentType;
	@ManyToOne(targetEntity=RentContract.class) 
	@JoinColumn(name="rentedContractId")
	private RentContract rentContract;

	
	
	public RentItem() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RentItem(Bike bike, RentType rentedTyme, Integer unitOfRentType) {
		super();
		this.bike = bike;
		this.rentedTyme = rentedTyme;
		this.unitOfRentType = unitOfRentType;
	}

	public Bike getBike() {
		return bike;
	}
	public void setBike(Bike bike) {
		this.bike = bike;
	}

	public RentType getRentedTyme() {
		return rentedTyme;
	}

	public RentContract getRentContract() {
		return rentContract;
	}
	
	public void setRentContract(RentContract rentContract) {
		this.rentContract = rentContract;
	}

	public Integer getUnitOfRentType() {
		return unitOfRentType;
	}

	public void setUnitOfRentType(Integer unitOfRentType) {
		this.unitOfRentType = unitOfRentType;
	}

	
	
}
