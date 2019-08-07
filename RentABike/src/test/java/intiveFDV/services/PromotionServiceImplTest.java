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
import intiveFDV.domain.Promotion;
import intiveFDV.domain.PromotionType;
import intiveFDV.domain.Promotion;
import intiveFDV.domain.RentType;
import intiveFDV.domain.TimeUnit;
import intiveFDV.dto.PromotionalRentRequestDto;
import intiveFDV.dto.RentedTimeRequestDto;
import intiveFDV.repositories.PromotionRepository;

@RunWith(SpringRunner.class)
public class PromotionServiceImplTest {

	
	@TestConfiguration
	static class PromotionServiceImplTestContextConfiguration {
		@Bean
	    public PromotionService PromotionService() {
			return new PromotionServiceImpl();
	    }
	}
	
	@Autowired
	private PromotionService promotionService;
	
	@MockBean
	PromotionRepository promotionRepository; 
	
	
	  Promotion promotion;
		
	@Before
	public void setUp() {
		promotion = new Promotion(PromotionType.FAMILY_RENT, 0.3D);
	}
	
	
	@Test
	public void getPromotion() {
		given(promotionRepository.findByType(Mockito.any(PromotionType.class))).willReturn(promotion);
		assertThat(promotionService.getPromotion(PromotionType.FAMILY_RENT).getDiscount()).isEqualTo(promotion.getDiscount());
	}
	
	
	
}
