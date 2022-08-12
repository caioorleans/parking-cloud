package cloudparking.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cloudparking.model.Parking;

@RestController
@RequestMapping("/parking")
public class ParkingController {
	
	@GetMapping
	public List<Parking> findAll(){
		
		Parking parking = new Parking();
		parking.setColor("vermelho");
		parking.setLicense("MSS-1111");
		parking.setModel("Fiat Uno");
		parking.setState("Ceará");
		
		return Arrays.asList(parking);
	}

}
