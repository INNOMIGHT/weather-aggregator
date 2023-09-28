package com.vaibhavshrivastava.weatherforecast.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FinalResponse {
	
	private AccuweatherDTO accuweatherDTO;


	private FeelsLikeDTO feels_like;
    private int pressure;
    private int humidity;
    private int visibility;
    private WindDTO wind;
    private long sunrise;
    private long sunset;
    
	public AccuweatherDTO getAccuweatherDTO() {
		return accuweatherDTO;
	}

	public void setAccuweatherDTO(AccuweatherDTO accuweatherDTO) {
		this.accuweatherDTO = accuweatherDTO;
	}

    public FeelsLikeDTO getFeels_like() {
        return feels_like;
    }

    public void setFeels_like(FeelsLikeDTO feels_like) {
        this.feels_like = feels_like;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public WindDTO getWind() {
        return wind;
    }

    public void setWind(WindDTO wind) {
        this.wind = wind;
    }

    public long getSunrise() {
        return sunrise;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
    }



}
