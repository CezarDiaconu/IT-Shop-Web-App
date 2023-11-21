package com.ITShopSpring.Devices;

import java.util.List;
import java.util.Map;

import org.apache.el.stream.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class DeviceControllerJpa {

	private DeviceRepository deviceRepository;

	public DeviceControllerJpa(DeviceRepository deviceRepository) {
		super();
		this.deviceRepository = deviceRepository;
	}
	
	@GetMapping("/devices")
	public List<Device> retrieveAllDevices() {
		return deviceRepository.findAll();
	}
	@GetMapping("/devices/{name}")
	public Device findByName(@PathVariable String name) {
		return deviceRepository.findByName(name);
	}
	
	
	@PostMapping("/add-device") 
	public Device addDevice(@RequestBody Map<String, Object> deviceData) {
		 String productName = (String) deviceData.get("productName");
		 String productBrand = (String) deviceData.get("productBrand");
		 String productDeviceType = (String)deviceData.get("productDeviceType");
		 int productPrice = Integer.parseInt((String) deviceData.get("productPrice"));
		 int productQuantity = Integer.parseInt((String) deviceData.get("productQuantity"));
		 
		 Device newDevice = new Device();
		 newDevice.setName(productName);
		 newDevice.setBrand(productBrand);
		 newDevice.setDeviceType(productDeviceType);
		 newDevice.setPrice(productPrice);
		 newDevice.setQuantity(productQuantity);
		 
		 return deviceRepository.save(newDevice);
	    }
	
	@PatchMapping("/update-device")
	public ResponseEntity<Device> updateDevice(@RequestBody Map<String, String> updateData) {
	    String productName = updateData.get("productName");
	    String attributeToChange = updateData.get("attributeToChange");
	    String newValue = updateData.get("newValue");
	    
	    
	    System.out.println("Received productName: " + productName);
	    System.out.println("Received attributeToChange: " + attributeToChange);
	    System.out.println("Received newValue: " + newValue);
	    
	    Device device = deviceRepository.findByName(productName);
	    
	    if(device != null) {
	    	switch (attributeToChange) {
	    	case "quantity":
	    		int newQuantity = Integer.parseInt(newValue);
                device.setQuantity(newQuantity);
                break;
	    	case "price":
	    		 int newPrice = Integer.parseInt(newValue);
	                device.setPrice(newPrice);
	                break;
	        default: break;
	    							   }
	    	deviceRepository.save(device);
	    	return ResponseEntity.ok(device);
	    	}
	    	else {
	    		return ResponseEntity.notFound().build();
	    	}
	}
		
	
	
}
	
	
	

