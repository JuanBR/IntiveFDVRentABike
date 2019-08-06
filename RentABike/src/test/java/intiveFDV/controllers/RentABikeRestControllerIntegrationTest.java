package intiveFDV.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import intiveFDV.domain.Bike;
import intiveFDV.domain.BikeStatus;
import intiveFDV.domain.Promocion;
import intiveFDV.domain.PromocionType;
import intiveFDV.domain.RentContract;
import intiveFDV.domain.RentItem;
import intiveFDV.domain.RentType;
import intiveFDV.domain.TimeUnit;
import intiveFDV.dto.PromocionalRentRequestDto;
import intiveFDV.dto.RentRequestDto;
import intiveFDV.dto.RentedTimeRequestDto;
import intiveFDV.services.RentABikeService;

import static org.mockito.BDDMockito.given;


@RunWith(SpringRunner.class)
@WebMvcTest(RentABikeController.class)
public class RentABikeRestControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;
	 
	@MockBean
	private RentABikeService rentABikeService;
	 
	// write test cases here
	
	@Test
    public void rentABikePerHour(@RequestParam(value="rentedHours", defaultValue="1") Integer rentedHours, @RequestParam(value="userId") String userId) throws Exception {
		
//		List<RentedTimeRequestDto> rentedTime = Arrays.asList( new RentedTimeRequestDto(TimeUnit.DAY, 1));
//		RentRequestDto request = new RentRequestDto("userID01", rentedTime);
	    mvc.perform(post("/api/rentABikePerHour?rentedHours=1,userId='041'")
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      ;
    }
	
	@Test
    public void rentABikePerDay(@RequestParam(value="rentedDays", defaultValue="1") Integer rentedDays, @RequestParam(value="userId") String userId) throws Exception {
		 mvc.perform(post("/api/rentABikePerDay?rentedHours=1,userId='041'")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      ;
    }
	
	@PostMapping("/rentABikePerWeek")
    public void rentABikePerWeek(@RequestParam(value="rentedWeeks", defaultValue="1") Integer rentedWeeks, @RequestParam(value="userId") String userId  ) throws Exception {
		 mvc.perform(post("/api/rentABikePerDay?rentedWeeks=1,userId='041'")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      ;
    }

	@PostMapping("/familyRent")
    public void familyRent(@Valid @RequestParam(value="rentItems") @Size(min = 3, max = 5) List<RentedTimeRequestDto> rentedTime, @RequestParam(value="userId") String userId) throws Exception {
		 mvc.perform(get("/api/familyRent?rentedHours=1,userId='041'")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      ;
    }	
	
	@GetMapping("/getAllRentContractsByUser")
    public void getAllRentContractsByUser(@RequestParam(value="userId") String userId) throws Exception {
    	List<RentContract> contractList = Arrays.asList(
    			new RentContract("014", Arrays.asList(
    										new RentItem(new Bike(1l, BikeStatus.RENDTED), new RentType(TimeUnit.DAY,0.3), 1))
    										, null));
	 
	    given(rentABikeService.getAllRentContractsByUser("014")).willReturn(contractList);
	 
	    mvc.perform(get("/api/getAllRentContractsByUser?userId='014'")
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      .andExpect(jsonPath("$.[0].userId", is(contractList.get(0).getUserId())));
		
    }
	
}
