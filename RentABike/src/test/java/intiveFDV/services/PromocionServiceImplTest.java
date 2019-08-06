package intiveFDV.services;

import static org.assertj.core.api.Assertions.anyOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import intiveFDV.domain.Promocion;
import intiveFDV.domain.PromocionType;
import intiveFDV.domain.Promocion;
import intiveFDV.domain.RentType;
import intiveFDV.domain.TimeUnit;
import intiveFDV.dto.PromocionalRentRequestDto;
import intiveFDV.dto.RentedTimeRequestDto;
import intiveFDV.repositories.PromocionRepository;

@RunWith(SpringRunner.class)
public class PromocionServiceImplTest {

	
	@TestConfiguration
	static class PromocionServiceImplTestContextConfiguration {
		@Bean
	    public PromocionService PromocionService() {
			return new PromocionServiceImpl();
	    }
	}
	
	@Autowired
	private PromocionService promocionService;
	
	@MockBean
	PromocionRepository promocionRepository; 
	
	
	  Promocion promocion;
		
	@Before
	public void setUp() {
		promocion = new Promocion(PromocionType.FAMILY_RENT, 0.3D);
	}
	
	
	@Test
	public void getPromocion() {
		given(promocionRepository.findByType(Mockito.any(PromocionType.class))).willReturn(promocion);
		assertThat(promocionService.getPromocion(PromocionType.FAMILY_RENT).getDiscount()).isEqualTo(promocion.getDiscount());
	}
	
	
	
}
