package com.vaibhavshrivastava.weatherforecast.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.vaibhavshrivastava.weatherforecast.WebClient.WebClientConfig;
import com.vaibhavshrivastava.weatherforecast.dto.AccuweatherCity;

import reactor.core.publisher.Mono;


@Service
public class WeatherForecastService {
	
	private final WebClient accuweatherCityApi1WebClient;

	
	@Autowired
	public WeatherForecastService(@Qualifier("accuweatherCityApi1WebClient") WebClient accuweatherCityApi1WebClient) {
		super();
		this.accuweatherCityApi1WebClient = accuweatherCityApi1WebClient;
	}
	
	private final ExecutorService executorService = Executors.newFixedThreadPool(2);
	
	public Object getLocationKey(String apiKey, String city) {
		Mono<String> responseMono = accuweatherCityApi1WebClient
                .get()
                .uri("/search?apikey={apiKey}&q={city}", apiKey, city)
                .retrieve()
                .bodyToMono(String.class);

        Object cityKey = responseMono.block();
		
		
        return cityKey;
	}
	
}
