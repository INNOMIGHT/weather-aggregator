package com.vaibhavshrivastava.weatherforecast.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaibhavshrivastava.weatherforecast.WebClient.WebClientConfig;
import com.vaibhavshrivastava.weatherforecast.dto.AccuweatherCity;
import com.vaibhavshrivastava.weatherforecast.service.WeatherForecastService;


@RestController
@RequestMapping("")
public class WeatherForecastController {
	
	@Autowired
	WeatherForecastService weatherForecastService;
	
	@GetMapping("/api1")
	public Object accuweatherStep1() {
		return weatherForecastService.getLocationKey("9XLd9Je0zc6YL6x362I3ivGXI7bIAuef", "Noida");
	}
}
