package com.ITShopSpring.Devices;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface DeviceRepository extends JpaRepository<Device, Integer> {
	
	public List<Device> findAll();
	public Device findByName(String name);
	
	
	<S extends Device> S save(S device);
}
