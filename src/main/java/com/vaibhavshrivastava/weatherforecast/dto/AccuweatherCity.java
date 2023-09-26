package com.vaibhavshrivastava.weatherforecast.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class AccuweatherCity {
	
	public String Key;
	

	public AccuweatherCity() {
		super();
	}

	public AccuweatherCity(String key) {
		super();
		Key = key;
	}

	public String getKey() {
		return Key;
	}

	public void setKey(String key) {
		Key = key;
	}
	
	
	
	
}
