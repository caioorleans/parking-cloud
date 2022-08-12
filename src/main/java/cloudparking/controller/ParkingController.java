package cloudparking.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cloudparking.controller.mapper.ParkingMapper;
import cloudparking.dto.ParkingDTO;
import cloudparking.model.Parking;
import cloudparking.service.ParkingService;

@RestController
@RequestMapping("/parking")
public class ParkingController {
	
	private final ParkingService service;
	private final ParkingMapper parkingMapper;
	
	public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
		this.service = parkingService;
		this.parkingMapper = parkingMapper;
	}
	
	@GetMapping
	public List<Parking> findAll(){
		List<Parking> parkingList = service.findAll();
		List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
	}

}
