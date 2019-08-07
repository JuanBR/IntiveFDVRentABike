package intiveFDV;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import intiveFDV.domain.Bike;
import intiveFDV.domain.BikeStatus;
import intiveFDV.domain.Promotion;
import intiveFDV.domain.PromotionType;
import intiveFDV.domain.RentType;
import intiveFDV.domain.TimeUnit;
import intiveFDV.repositories.BikeRepository;
import intiveFDV.repositories.PromotionRepository;
import intiveFDV.repositories.RentContractRepository;
import intiveFDV.repositories.RentTypeRepository;

@Component
@Profile("!test")
public class DataLoader implements ApplicationRunner {
	@Autowired
	private BikeRepository bikeRepository;
	@Autowired
	private PromotionRepository promotionRepository;
	@Autowired
	private RentTypeRepository rentTypeRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Bike bike1 = new Bike(BikeStatus.AVAILABLE);
    	Bike bike2 = new Bike(BikeStatus.AVAILABLE);
    	Bike bike3 = new Bike(BikeStatus.AVAILABLE);
    	Bike bike4 = new Bike(BikeStatus.AVAILABLE);
    	Bike bike5 = new Bike(BikeStatus.AVAILABLE);
    	Bike bike6 = new Bike(BikeStatus.AVAILABLE);
		
    	bikeRepository.save(bike1);
    	bikeRepository.save(bike2);
    	bikeRepository.save(bike3);
    	bikeRepository.save(bike4);
    	bikeRepository.save(bike5);
    	bikeRepository.save(bike6);
    	
    	
    	Promotion promotion = new Promotion(PromotionType.FAMILY_RENT, 0.3D);
    	promotionRepository.save(promotion);
    	
    	RentType rentTypeHour = new RentType(TimeUnit.HOUR,0.4D);
    	RentType rentTypeDay = new RentType(TimeUnit.DAY,0.3D);
    	RentType rentTypeWeek = new RentType(TimeUnit.WEEK,0.2D);
    	
    	rentTypeRepository.save(rentTypeHour);
    	rentTypeRepository.save(rentTypeDay);
    	rentTypeRepository.save(rentTypeWeek);
    	
	}

}
