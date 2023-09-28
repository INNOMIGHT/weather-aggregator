package com.vaibhavshrivastava.weatherforecast.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.vaibhavshrivastava.weatherforecast.dto.AccuweatherDTO;
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

	private RequestHeadersUriSpec uriSpecMock;
	private WebClient.RequestHeadersSpec headersSpecMock;
	private WebClient.ResponseSpec responseSpecMock;

	WebClient accuweatherCityApi1WebClient = mock(WebClient.class);
	WebClient accuweatherConditionApi2WebClient = mock(WebClient.class);
	WebClient openWeatherLocationApi3WebClient = mock(WebClient.class);
	WebClient openWeatherInfoApi4WebClient = mock(WebClient.class);

	@BeforeEach
	public void setUp() {
		uriSpecMock = Mockito.mock(WebClient.RequestHeadersUriSpec.class);
		headersSpecMock = Mockito.mock(WebClient.RequestHeadersSpec.class);
		responseSpecMock = Mockito.mock(WebClient.ResponseSpec.class);

		when(accuweatherCityApi1WebClient.get()).thenReturn(uriSpecMock);
		when(uriSpecMock.uri(anyString(), anyString(), anyString())).thenReturn(headersSpecMock);
		when(headersSpecMock.retrieve()).thenReturn(responseSpecMock);

		weatherForecastService = new WeatherForecastService(accuweatherCityApi1WebClient, accuweatherConditionApi2WebClient, openWeatherLocationApi3WebClient, openWeatherInfoApi4WebClient);
	}

	/*
	[
		{
			"key": "3146227"
		}
	]
	 */
	@Test
	public void itShouldReturnCityKeyAsAString() throws Exception {
		String apiKey = "9XLd9Je0zc6YL6x362I3ivGXI7bIAuef";
		String city = "Noida";

		when(responseSpecMock.bodyToMono(ArgumentMatchers.<Class<String>>notNull()))
				.thenReturn(Mono.just("""
						[
							{
								"Key": "3146227"
							}
						]
						"""));

		String cityKey = weatherForecastService.getLocationKey(apiKey, city);

		assertEquals(cityKey, "3146227");
	}

	@Test
	public void itShouldGetConditionFromExternalAPI() throws Exception {
		String apiKey = "9XLd9Je0zc6YL6x362I3ivGXI7bIAuef";
		String cityKey = "3146227";

		when(accuweatherConditionApi2WebClient.get()).thenReturn(uriSpecMock);
		when(responseSpecMock.bodyToMono(ArgumentMatchers.<Class<String>>notNull()))
				.thenReturn(Mono.just("""
						[
						   {
						     "LocalObservationDateTime": "2023-09-28T16:53:00+05:30",
						     "EpochTime": 1695900180,
						     "WeatherText": "Sunny",
						     "WeatherIcon": 1,
						     "HasPrecipitation": false,
						     "PrecipitationType": null,
						     "IsDayTime": true,
						     "Temperature": {
						       "Metric": {
						         "Value": 36.2,
						         "Unit": "C",
						         "UnitType": 17
						       },
						       "Imperial": {
						         "Value": 97,
						         "Unit": "F",
						         "UnitType": 18
						       }
						     },
						     "MobileLink": "http://www.accuweather.com/en/in/noida/3146227/current-weather/3146227?lang=en-us",
						     "Link": "http://www.accuweather.com/en/in/noida/3146227/current-weather/3146227?lang=en-us"
						   }
						 ]
						"""));

		AccuweatherDTO output = weatherForecastService.getConditions(cityKey, apiKey);
		System.out.println(output);

		assertNotNull(output);
		assertEquals("Sunny", output.getWeatherText());
	}


}
