package intiveFDV;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import intiveFDV.domain.Bike;
import intiveFDV.domain.BikeStatus;
import intiveFDV.domain.Promocion;
import intiveFDV.domain.PromocionType;
import intiveFDV.domain.RentType;
import intiveFDV.domain.TimeUnit;
import intiveFDV.repositories.BikeRepository;
import intiveFDV.repositories.PromocionRepository;
import intiveFDV.repositories.RentContractRepository;
import intiveFDV.repositories.RentTypeRepository;

@Component
public class DataLoader implements ApplicationRunner {
	
	@Autowired
	private BikeRepository bikeRepository;
	@Autowired
	private PromocionRepository promocionRepository;
	@Autowired
	private RentContractRepository rentContractRepository;
	@Autowired
	private RentTypeRepository rentTypeRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Bike bike1 = new Bike(BikeStatus.AVALAIBLE);
    	Bike bike2 = new Bike(BikeStatus.AVALAIBLE);
    	Bike bike3 = new Bike(BikeStatus.AVALAIBLE);
    	Bike bike4 = new Bike(BikeStatus.AVALAIBLE);
    	Bike bike5 = new Bike(BikeStatus.AVALAIBLE);
    	Bike bike6 = new Bike(BikeStatus.AVALAIBLE);
		
    	bikeRepository.save(bike1);
    	bikeRepository.save(bike2);
    	bikeRepository.save(bike3);
    	bikeRepository.save(bike4);
    	bikeRepository.save(bike5);
    	bikeRepository.save(bike6);
    	
    	
    	Promocion promocion = new Promocion(PromocionType.FAMILY_RENT, 0.3D);
    	promocionRepository.save(promocion);
    	
    	RentType rentTypeHour = new RentType(TimeUnit.HOUR,0.4D);
    	RentType rentTypeDay = new RentType(TimeUnit.DAY,0.3D);
    	RentType rentTypeWeek = new RentType(TimeUnit.WEEK,0.2D);
    	
    	rentTypeRepository.save(rentTypeHour);
    	rentTypeRepository.save(rentTypeDay);
    	rentTypeRepository.save(rentTypeWeek);
    	
	}

}
