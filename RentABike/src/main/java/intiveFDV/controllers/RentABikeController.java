package intiveFDV.controllers;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import intiveFDV.domain.PromocionType;
import intiveFDV.domain.RentContract;
import intiveFDV.domain.TimeUnit;
import intiveFDV.dto.PromocionalRentRequestDto;
import intiveFDV.dto.RentRequestDto;
import intiveFDV.dto.RentedTimeRequestDto;
import intiveFDV.services.RentABikeService;

@Validated
@RestController
@RequestMapping("/api")
public class RentABikeController {
	private static final Logger log = LogManager.getLogger(RentABikeController.class);

	@Autowired
    private RentABikeService rentABikeService;
 
	
	@PostMapping("/rentABikePerHour")
    public void rentABikePerHour(@RequestParam(value="rentedHours", defaultValue="1") Integer rentedHours, @RequestParam(value="userId") String userId) {
		RentRequestDto request = new RentRequestDto(userId, Arrays.asList(new RentedTimeRequestDto(TimeUnit.HOUR, rentedHours)));
		rentABikeService.rentABike(request);
    }
	
	@PostMapping("/rentABikePerDay")
    public void rentABikePerDay(@RequestParam(value="rentedDays", defaultValue="1") Integer rentedDays, @RequestParam(value="userId") String userId) {
		RentRequestDto request = new RentRequestDto(userId, Arrays.asList(new RentedTimeRequestDto(TimeUnit.DAY, rentedDays)));
		rentABikeService.rentABike(request);
    }
	
	@PostMapping("/rentABikePerWeek")
    public void rentABikePerWeek(@RequestParam(value="rentedWeeks", defaultValue="1") Integer rentedWeeks, @RequestParam(value="userId") String userId  ) {
		RentRequestDto request = new RentRequestDto(userId, Arrays.asList(new RentedTimeRequestDto(TimeUnit.WEEK, rentedWeeks)));
		rentABikeService.rentABike(request);
    }

	@PostMapping("/familyRent")
    public void familyRent(@Valid @RequestParam(value="rentItems") @Size(min = 3, max = 5) List<RentedTimeRequestDto> rentedTime, @RequestParam(value="userId") String userId) {
		PromocionalRentRequestDto request = new PromocionalRentRequestDto(userId, rentedTime, PromocionType.FAMILY_RENT);
		rentABikeService.rentABikeWithAPromocion(request);
    }	
	
	@GetMapping("/getAllRentContractsByUser")
    public List<RentContract> getAllRentContractsByUser(@RequestParam(value="userId") String userId) {
        return rentABikeService.getAllRentContractsByUser(userId);
    }
	
}
