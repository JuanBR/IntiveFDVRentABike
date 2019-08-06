package intiveFDV.services;

import static org.assertj.core.api.Assertions.anyOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.hibernate.boot.model.source.spi.AnyDiscriminatorSource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;


import intiveFDV.domain.Bike;
import intiveFDV.domain.BikeStatus;
import intiveFDV.exception.NotEnoughBikesException;
import intiveFDV.repositories.BikeRepository;
import intiveFDV.services.BikeService;
import intiveFDV.services.BikeServiceImpl;

@RunWith(SpringRunner.class)
public class BikeServiceImplTest {

	
	@TestConfiguration
	static class BikeServiceImplTestContextConfiguration {
		@Bean
	    public BikeService bikeService() {
			return new BikeServiceImpl();
	    }
	}
	
	@Autowired
	private BikeService bikeService;
	
	@MockBean
	private BikeRepository bikeRepository;
	

	
	@Test
	public void givmeBikesAreEnoughBike(){
		// given
    	Bike bike1 = new Bike(BikeStatus.AVALAIBLE);
    	Bike bike2 = new Bike(BikeStatus.AVALAIBLE);
    	Optional<List<Bike>> listbike = Optional.of(Arrays.asList(bike1, bike2));
    	
		given(bikeRepository.findAvalaible()).willReturn(listbike);
		  // when
        List<Bike> found = bikeService.givmeBikes(2);
     
        // then
        assertThat(found.size()).isEqualTo(listbike.get().size());
        
	}
	
	@Test(expected = NotEnoughBikesException.class)
	public void givmeBikesThereAreNotEnoughBike(){
		// given
    	Bike bike1 = new Bike(BikeStatus.AVALAIBLE);
    	Optional<List<Bike>> listbike = Optional.of(Arrays.asList(bike1));
    	
		given(bikeRepository.findAvalaible()).willReturn(listbike);
		  // when
        List<Bike> found = bikeService.givmeBikes(2);
     
	}
	
	
	
}
