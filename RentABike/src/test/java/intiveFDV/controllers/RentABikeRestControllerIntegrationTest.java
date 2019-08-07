package intiveFDV.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.NestedServletException;

import com.fasterxml.jackson.databind.ObjectMapper;

import intiveFDV.domain.Bike;
import intiveFDV.domain.BikeStatus;
import intiveFDV.domain.Promotion;
import intiveFDV.domain.PromotionType;
import intiveFDV.domain.RentContract;
import intiveFDV.domain.RentItem;
import intiveFDV.domain.RentType;
import intiveFDV.domain.TimeUnit;
import intiveFDV.dto.PromotionalRentRequestDto;
import intiveFDV.dto.RentRequestDto;
import intiveFDV.dto.RentedTimeRequestDto;
import intiveFDV.exception.InvalidPromotionListSizeExcetion;
import intiveFDV.exception.NotEnoughBikesException;
import intiveFDV.services.RentABikeService;

@RunWith(SpringRunner.class)
@WebMvcTest
@ContextConfiguration(classes = {RentABikeController.class})
public class RentABikeRestControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;
	 
	@MockBean
	private RentABikeService rentABikeService;
	
	@Autowired private ObjectMapper mapper;
	
	
	@Test
    public void rentABikePerHour() throws Exception {
		Mockito.doNothing().when(rentABikeService).rentABike(Mockito.any(RentRequestDto.class));
		 mvc.perform(post("/api/rentABikePerHour?userId=40")
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      ;
		 Mockito.verify(rentABikeService, Mockito.times(1)).rentABike(Mockito.any(RentRequestDto.class));
    }

	@Test
    public void rentABikePerDay() throws Exception {
		Mockito.doNothing().when(rentABikeService).rentABike(Mockito.any(RentRequestDto.class));
		 mvc.perform(post("/api/rentABikePerDay?userId=40")
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      ;
		 Mockito.verify(rentABikeService, Mockito.times(1)).rentABike(Mockito.any(RentRequestDto.class));
    }
	
	@Test
    public void rentABikePerWeek() throws Exception {
		Mockito.doNothing().when(rentABikeService).rentABike(Mockito.any(RentRequestDto.class));
		 mvc.perform(post("/api/rentABikePerWeek?userId=40")
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      ;
		 Mockito.verify(rentABikeService, Mockito.times(1)).rentABike(Mockito.any(RentRequestDto.class));
    }

	@Test(expected = NestedServletException.class)
    public void familyRentInvalidPromotionListSize() throws Exception {
		Mockito.doNothing().when(rentABikeService).rentABikeWithAPromotion(Mockito.any(PromotionalRentRequestDto.class));
		RentRequestDto rentRequest = new RentRequestDto(
				"user", Arrays.asList(new RentedTimeRequestDto(TimeUnit.DAY, 3)));
		String json = mapper.writeValueAsString(rentRequest);
		
		mvc.perform(post("/api/familyRent")
				.content(json)
	      .contentType(MediaType.APPLICATION_JSON))
	    
	      ;
		
    }
	
	@Test
    public void familyRent() throws Exception {
		Mockito.doNothing().when(rentABikeService).rentABikeWithAPromotion(Mockito.any(PromotionalRentRequestDto.class));
		RentRequestDto rentRequest = new RentRequestDto(
				"user", Arrays.asList(new RentedTimeRequestDto(TimeUnit.DAY, 3), new RentedTimeRequestDto(TimeUnit.HOUR, 2), new RentedTimeRequestDto(TimeUnit.WEEK, 1)));
		String json = mapper.writeValueAsString(rentRequest);
		mvc.perform(post("/api/familyRent")
				.content(json)
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      ;
		 Mockito.verify(rentABikeService, Mockito.times(1)).rentABikeWithAPromotion(Mockito.any(PromotionalRentRequestDto.class));
    }	
	
	@Test
    public void getAllRentContractsByUser() throws Exception {
		 Bike bike = new Bike(BikeStatus.AVAILABLE);
		 RentType rentType = new RentType(TimeUnit.DAY, 12.0D);
		 RentItem rentItem = new RentItem(bike, rentType, 3);
		 List<RentItem> rentItems = Arrays.asList(rentItem );
		 Promotion promotion = new Promotion(PromotionType.FAMILY_RENT, 0.3D);
		 RentContract rentContract = new RentContract("user40", rentItems, promotion);
		 
		 List<RentContract> listrentContract = Arrays.asList(rentContract);
	 
	    given(rentABikeService.getAllRentContractsByUser(Mockito.anyString())).willReturn(listrentContract);
	 
	    mvc.perform(get("/api/getAllRentContractsByUser?userId=user40")
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      .andExpect(jsonPath("$", hasSize(1)))
	      .andExpect(jsonPath("$[0].userId", is(rentContract.getUserId())));
    }
	
}
