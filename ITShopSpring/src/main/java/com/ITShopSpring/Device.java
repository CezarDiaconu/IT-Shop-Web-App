package com.ITShopSpring;

public class Device {

	private String name;
	private String brand;
	private String deviceType;
	private int price;
	
	public Device(String name, String brand, String deviceType, int price) {
		super();
		this.name = name;
		this.brand = brand;
		this.deviceType = deviceType;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Device [name=" + name + ", brand=" + brand + ", deviceType=" + deviceType + ", price=" + price + "]";
	}
	
	
	
}
