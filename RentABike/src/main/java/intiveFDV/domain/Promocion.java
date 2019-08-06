package intiveFDV.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Promocion")
public class Promocion {

	private Long id;
	private PromocionType type;
	private Double discount;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public PromocionType getType() {
		return type;
	}
	public void setType(PromocionType name) {
		this.type = name;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Promocion(PromocionType type, Double discount) {
		super();
		this.type = type;
		this.discount = discount;
	}
	public Promocion() {
		super();
	}
	
	
	
}
