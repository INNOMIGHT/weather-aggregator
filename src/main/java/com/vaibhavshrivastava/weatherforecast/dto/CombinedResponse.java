package com.vaibhavshrivastava.weatherforecast.dto;


public class CombinedResponse {
	
	private AccuweatherDTO accuweatherDTO;
	private OpenWeatherInfoDTO openWeatherInfoDTO;
	public AccuweatherDTO getAccuweatherDTO() {
		return accuweatherDTO;
	}
	public void setAccuweatherDTO(AccuweatherDTO accuweatherDTO) {
		this.accuweatherDTO = accuweatherDTO;
	}
	public OpenWeatherInfoDTO getOpenWeatherInfoDTO() {
		return openWeatherInfoDTO;
	}
	public void setOpenWeatherInfoDTO(OpenWeatherInfoDTO openWeatherInfoDTO) {
		this.openWeatherInfoDTO = openWeatherInfoDTO;
	}
	
	
}
