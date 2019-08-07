package intiveFDV.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Promotion")
public class Promotion {

	private Long id;
	private PromotionType type;
	private Double discount;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public PromotionType getType() {
		return type;
	}
	public void setType(PromotionType name) {
		this.type = name;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Promotion(PromotionType type, Double discount) {
		super();
		this.type = type;
		this.discount = discount;
	}
	public Promotion() {
		super();
	}
	
	
	
}
