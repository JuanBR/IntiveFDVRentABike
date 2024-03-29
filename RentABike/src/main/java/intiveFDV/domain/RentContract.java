package intiveFDV.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.lang.Nullable;

@Entity
@Table(name = "RentContract")
public class RentContract {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String userId;
	private Double totalPrice;
	private Double totalDiscount;
	private Double finalPrice;
	@OneToMany(targetEntity=RentItem.class, mappedBy="rentContract")
	private List<RentItem> rentedItems;
	@OneToOne(targetEntity=Promotion.class, optional = true)
	@JoinColumn(name="promotionId")
	private Promotion promotion;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Double getTotalDiscount() {
		return totalDiscount;
	}
	public void setTotalDiscount(Double totalDiscount) {
		this.totalDiscount = totalDiscount;
	}
	public Double getEndPrice() {
		return finalPrice;
	}
	public void setEndPrice(Double endPrice) {
		this.finalPrice = endPrice;
	}
	public List<RentItem> getRentedItems() {
		return rentedItems;
	}
	public void setRentedItems(List<RentItem> rentedItems) {
		this.rentedItems = rentedItems;
	}
	public Promotion getPromotion() {
		return promotion;
	}
	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}
	
	public RentContract(String user, List<RentItem> rentedItems, Promotion promotion) {
		super();
		this.userId = user;
		this.rentedItems = rentedItems;
		this.promotion = promotion;
		calculateFinalPrice();
	}
	private void calculateFinalPrice() {
		this.totalPrice = 0d;
		this.totalPrice = this.rentedItems.stream()
				.reduce(0d, (subtotal, rentItem) -> 
				subtotal + rentItem.getUnitOfRentType() * rentItem.getRentedTyme().getChargePerTymeUnit(), Double::sum);
		this.totalDiscount = this.totalPrice * (this.getPromotion() !=null ? this.getPromotion().getDiscount() : 0);
		this.finalPrice = this.totalPrice - this.totalDiscount;
		
	}
	public RentContract() {
		super();
	}

	
	
	
	
	
	
	
}
