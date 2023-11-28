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
	
	@PatchMapping("/buy-device")
	public ResponseEntity<Device> buyDevice(@RequestBody Map<String, String> deviceInfo) {
	    String productName = deviceInfo.get("productName");

	    if (productName != null) {
	        Device device = deviceRepository.findByName(productName);

	        if (device != null && device.getQuantity() > 0) {
	            device.setQuantity(device.getQuantity() - 1);
	            deviceRepository.save(device);
	            return ResponseEntity.ok(device);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    } else {
	        return ResponseEntity.badRequest().build();
	    }
	}
	@PatchMapping("/update-device")
	public ResponseEntity<Device> updateDevice(@RequestBody Map<String, String> deviceInfo){
		String productName = deviceInfo.get("productName");
		String attributeToChange = deviceInfo.get("attributeToChange");
		String newValue = deviceInfo.get("newValue");
		
		if (productName != null && attributeToChange != null && newValue != null) {
	        Device device = deviceRepository.findByName(productName);
		
	        if(device != null)
	        {
	        	switch (attributeToChange) {
			  	case "brand":
			  		device.setBrand(newValue);
			  		break;
			  	case "deviceType":
			  		device.setDeviceType(newValue);
			  		break;
			  	case "price":
			  		int newPrice = 0; 
                    try {
                        newPrice = Integer.parseInt(newValue);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    device.setPrice(newPrice);
                    break;
			  	case "quantity":
			  		int newQuantity = 0; 
                    try {
                        newQuantity = Integer.parseInt(newValue);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    device.setPrice(newQuantity);
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
		return ResponseEntity.notFound().build(); 
	}

		
	
	
}
	
	
	

