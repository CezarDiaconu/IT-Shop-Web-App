package com.ITShopSpring.Users.junit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ITShopSpring.Devices.DeviceControllerJpa;
import com.ITShopSpring.Devices.DeviceRepository;

class DeviceControllerTest {

	MockMvc mockMvc;
	
	 @Mock
	 private DeviceRepository deviceRepository; // Mock your repository or use a test repository

	 @InjectMocks
	 private DeviceControllerJpa deviceController; // The controller to be tested
	
	@BeforeEach
	void test() {
		MockitoAnnotations.openMocks(this); // Initialize mock objects

        // Set up MockMvc with standalone configuration for the controller
        this.mockMvc = MockMvcBuilders.standaloneSetup(deviceController).build();
	}
	
	 @Test
	    void testRetrieveAllDevices() throws Exception {
	        // Mock behavior of the repository method
	        when(deviceRepository.findAll()).thenReturn(Collections.emptyList());

	        // Perform a GET request and expect a certain status code
	        mockMvc.perform(get("/devices"))
	                .andExpect(status().isOk());
	        // Add more assertions based on the expected behavior
	    }

}
