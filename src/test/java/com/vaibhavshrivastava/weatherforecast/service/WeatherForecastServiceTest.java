package com.vaibhavshrivastava.weatherforecast.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;

import reactor.core.publisher.Mono;



public class WeatherForecastServiceTest {

	
	private WeatherForecastService weatherForecastService;
	WebClient accuweatherCityApi1WebClient = mock(WebClient.class);
	WebClient accuweatherConditionApi2WebClient = mock(WebClient.class);
	WebClient openWeatherLocationApi3WebClient = mock(WebClient.class);
	WebClient openWeatherInfoApi4WebClient = mock(WebClient.class);
	
	@BeforeEach
	public void setUp() {
		
		
		
	}
	
	@Test
	public void itShouldReturnCityKeyAsAString() throws Exception {
		
		
		weatherForecastService = new WeatherForecastService(accuweatherCityApi1WebClient, accuweatherConditionApi2WebClient, openWeatherLocationApi3WebClient, openWeatherInfoApi4WebClient);
		String city = "Noida";
		String apiKey = "9XLd9Je0zc6YL6x362I3ivGXI7bIAuef";
		
	    final var uriSpecMock = Mockito.mock(WebClient.RequestHeadersUriSpec.class);
	    final var headersSpecMock = Mockito.mock(WebClient.RequestHeadersSpec.class);
	    final var responseSpecMock = Mockito.mock(WebClient.ResponseSpec.class);

	    when(accuweatherCityApi1WebClient.get()).thenReturn(uriSpecMock);
	    when(uriSpecMock.uri(ArgumentMatchers.<String>notNull())).thenReturn(headersSpecMock);
	    when(headersSpecMock.retrieve()).thenReturn(responseSpecMock);
	    when(responseSpecMock.bodyToMono(ArgumentMatchers.<Class<String>>notNull()))
	            .thenReturn(Mono.just("3146227"));


		System.out.println(weatherForecastService);
		String cityKey = weatherForecastService.getLocationKey(apiKey, city);
		assertEquals(cityKey, "3146227");
	}
	
	
}
