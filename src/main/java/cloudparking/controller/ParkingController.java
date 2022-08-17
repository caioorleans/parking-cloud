package cloudparking.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cloudparking.controller.mapper.ParkingMapper;
import cloudparking.dto.ParkingCreateDTO;
import cloudparking.dto.ParkingDTO;
import cloudparking.model.Parking;
import cloudparking.service.ParkingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/parking")
@Api(tags = "Parking Controller")
public class ParkingController {
	
	private final ParkingService service;
	private final ParkingMapper parkingMapper;
	
	public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
		this.service = parkingService;
		this.parkingMapper = parkingMapper;
	}
	
	@GetMapping
	@ApiOperation("Find all parkings")
	public ResponseEntity<List<ParkingDTO>> findAll(){
		List<Parking> parkingList = service.findAll();
		List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/{id}")
	@ApiOperation("Find parking by id")
	public ResponseEntity<ParkingDTO> findById(@PathVariable String id){
		Parking parking = service.findById(id);
		ParkingDTO parkingDTO = parkingMapper.toParkingDTO(parking);
		return ResponseEntity.ok(parkingDTO);
	}
	
	@PostMapping("/save")
	@ApiOperation("Saves a new parking")
	public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO parkingDTO){
		Parking parkingCreate = parkingMapper.toParking(parkingDTO);
		Parking parking = service.create(parkingCreate);
		ParkingDTO result = parkingMapper.toParkingDTO(parking);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}

}
