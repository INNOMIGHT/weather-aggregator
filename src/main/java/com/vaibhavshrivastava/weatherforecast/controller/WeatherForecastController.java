package com.vaibhavshrivastava.weatherforecast.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vaibhavshrivastava.weatherforecast.WebClient.WebClientConfig;
import com.vaibhavshrivastava.weatherforecast.service.WeatherForecastService;


@RestController
@RequestMapping("/weather")
public class WeatherForecastController {
	
	@Autowired
	WeatherForecastService weatherForecastService;
	
	@GetMapping
	public Object accuweatherStep1(@RequestParam("city") String city, @RequestParam("zip") String zip) throws Exception {
		String[] zipParts = zip.split(",");
        
        String zipCode = zipParts[0];
        String countryCode = zipParts[1];

		return weatherForecastService.getFinalResponse(city, zipCode, countryCode);
	}
}
