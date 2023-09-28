package com.vaibhavshrivastava.weatherforecast.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaibhavshrivastava.weatherforecast.WebClient.WebClientConfig;
import com.vaibhavshrivastava.weatherforecast.dto.AccuweatherDTO;
import com.vaibhavshrivastava.weatherforecast.dto.FeelsLikeDTO;
import com.vaibhavshrivastava.weatherforecast.dto.FinalResponse;
import com.vaibhavshrivastava.weatherforecast.dto.OpenWeatherInfoDTO;
import com.vaibhavshrivastava.weatherforecast.dto.TemperatureDTO;

import PayloadTransformer.WeatherTransformer;
import reactor.core.publisher.Mono;


@Service
public class WeatherForecastService {

	private final WebClient accuweatherCityApi1WebClient;
	private final WebClient accuweatherConditionApi2WebClient;
	private final WebClient openWeatherLocationApi3WebClient;
	private final WebClient openWeatherInfoApi4WebClient;


	@Autowired
	public WeatherForecastService(@Qualifier("accuweatherCityApi1WebClient") WebClient accuweatherCityApi1WebClient, @Qualifier("accuweatherConditionApi2WebClient") WebClient accuweatherConditionApi2WebClient, @Qualifier("openWeatherLocationApi3WebClient") WebClient openWeatherLocationApi3WebClient, @Qualifier("openWeatherInfoApi4WebClient") WebClient openWeatherInfoApi4WebClient) {
		super();
		this.accuweatherCityApi1WebClient = accuweatherCityApi1WebClient;
		this.accuweatherConditionApi2WebClient = accuweatherConditionApi2WebClient;
		this.openWeatherLocationApi3WebClient = openWeatherLocationApi3WebClient;
		this.openWeatherInfoApi4WebClient = openWeatherInfoApi4WebClient;
	}

	private final ExecutorService executorService = Executors.newFixedThreadPool(2);

	public String getLocationKey(String apiKey, String city) throws Exception{
		Mono<String> responseMono = accuweatherCityApi1WebClient
                .get()
                .uri("/search?apikey={apiKey}&q={city}", apiKey, city)
                .retrieve()
                .bodyToMono(String.class);

        String responseJson = responseMono.block();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNodes = objectMapper.readTree(responseJson);

        // Extract the "Key" property from the first object in the array
        String key = jsonNodes.get(0).get("Key").asText();

        System.out.println("Key: " + key);

        return key;
	}

	public AccuweatherDTO getConditions(String cityKey, String apiKey) throws Exception {

		Mono<String> responseMono = accuweatherConditionApi2WebClient
                .get()
                .uri("/{cityKey}?apikey={apiKey}", cityKey, apiKey)
                .retrieve()
                .bodyToMono(String.class);

        String weatherConditions = responseMono.block();

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonNodes = objectMapper.readTree(weatherConditions);

        // Create a WeatherDTO object and extract the properties
        AccuweatherDTO weatherDTO = new AccuweatherDTO();
        JsonNode jsonNode = jsonNodes.get(0); // Assuming there's only one object in the array

        weatherDTO.setWeatherText(jsonNode.get("WeatherText").asText());
        weatherDTO.setHasPrecipitation(jsonNode.get("HasPrecipitation").asBoolean());
        weatherDTO.setPrecipitationType(jsonNode.get("PrecipitationType").asText());
        weatherDTO.setDayTime(jsonNode.get("IsDayTime").asBoolean());

        TemperatureDTO temperatureDTO = new TemperatureDTO();
        temperatureDTO.setValue(jsonNode.get("Temperature").get("Metric").get("Value").asDouble());
        temperatureDTO.setUnit(jsonNode.get("Temperature").get("Metric").get("Unit").asText());
        weatherDTO.setTemperature(temperatureDTO);

        // Print or use the WeatherDTO as needed
        System.out.println(weatherDTO);

        return weatherDTO;
	}

	public List<String> getLatitudeAndLongitude(String zipCode, String countryCode, String apiKey) throws Exception{
		Mono<String> responseMono = openWeatherLocationApi3WebClient
                .get()
                .uri("/zip?zip={zipCode},{countryCode}&appid={apiKey}", zipCode, countryCode, apiKey)
                .retrieve()
                .bodyToMono(String.class);

		List<String> latitudeAndLongitude = new ArrayList<String>();

        String locationDetails = responseMono.block();

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonNodes = objectMapper.readTree(locationDetails);

        // Extract the "Key" property from the first object in the array
        String lat = jsonNodes.get("lat").asText();
        String lon = jsonNodes.get("lon").asText();

        latitudeAndLongitude.add(lat);
        latitudeAndLongitude.add(lon);

        return latitudeAndLongitude;
	}


	public OpenWeatherInfoDTO getWeatherInfo(String lat, String lon, String apiKey) throws IOException {

		Mono<String> responseMono = openWeatherInfoApi4WebClient
                .get()
                .uri("/weather?lat={lat}&lon={lon}&appid={apiKey}", lat, lon, apiKey)
                .retrieve()
                .bodyToMono(String.class);

		String openWeatherInfo = responseMono.block();

        ObjectMapper objectMapper = new ObjectMapper();
        OpenWeatherInfoDTO weatherInfoDTO = objectMapper.readValue(openWeatherInfo, OpenWeatherInfoDTO.class);
        return weatherInfoDTO;
    }


	WeatherTransformer<AccuweatherDTO, OpenWeatherInfoDTO, FinalResponse> weatherTransformer = (accuweatherApiResponse, openWeatherApiResponse) -> {
		FinalResponse finalResponse = new FinalResponse();
		finalResponse.setAccuweatherDTO(accuweatherApiResponse);

		FeelsLikeDTO feelsLikeDTO = new FeelsLikeDTO();

		double feelsLikeTemp = openWeatherApiResponse.getMainData().getFeelsLike();
		feelsLikeTemp = feelsLikeTemp - 32 * (5/9);
		feelsLikeDTO.setValue(feelsLikeTemp);
		feelsLikeDTO.setUnit("C");
		finalResponse.setFeels_like(feelsLikeDTO);
		finalResponse.setPressure(openWeatherApiResponse.getMainData().getPressure());
		finalResponse.setHumidity(openWeatherApiResponse.getMainData().getHumidity());
		finalResponse.setVisibility(openWeatherApiResponse.getVisibility());
		finalResponse.setWind(openWeatherApiResponse.getWind());
		finalResponse.setSunrise(openWeatherApiResponse.getSys().getSunrise());
		finalResponse.setSunset(openWeatherApiResponse.getSys().getSunset());

		return finalResponse;

	};


	public FinalResponse getFinalResponse(String cityName, String zipCode, String countryCode) throws Exception {

		String accuweatherApiKey = "9XLd9Je0zc6YL6x362I3ivGXI7bIAuef";
		String openWeatherApiKey = "b571afb97f83bbc18515839acc585ff6";

		String cityKey = this.getLocationKey(accuweatherApiKey, cityName);
		AccuweatherDTO accuweatherApiResponse = this.getConditions(cityKey, accuweatherApiKey);
		List<String> latitudeAndLongitude = this.getLatitudeAndLongitude(zipCode, countryCode, openWeatherApiKey);
		String lat = latitudeAndLongitude.get(0);
		String lon = latitudeAndLongitude.get(1);
		OpenWeatherInfoDTO openWeatherApiResponse = this.getWeatherInfo(lat, lon, openWeatherApiKey);


		return weatherTransformer.transform(accuweatherApiResponse, openWeatherApiResponse);


	}


}
