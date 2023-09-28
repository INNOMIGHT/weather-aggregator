package com.vaibhavshrivastava.weatherforecast.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AccuweatherDTO {

	private String weatherText;
    private boolean HasPrecipitation;
    private String PrecipitationType;
    private boolean IsDayTime;
    private TemperatureDTO Temperature;
    // Getters and setters for the fields
    public String getWeatherText() {
        return weatherText;
    }

    public void setWeatherText(String weatherText) {
        this.weatherText = weatherText;
    }

    public boolean isHasPrecipitation() {
        return HasPrecipitation;
    }

    public void setHasPrecipitation(boolean hasPrecipitation) {
        HasPrecipitation = hasPrecipitation;
    }

    public String getPrecipitationType() {
        return PrecipitationType;
    }

    public void setPrecipitationType(String precipitationType) {
        PrecipitationType = precipitationType;
    }

    public boolean isDayTime() {
        return IsDayTime;
    }

    public void setDayTime(boolean dayTime) {
        IsDayTime = dayTime;
    }

    public TemperatureDTO getTemperature() {
        return Temperature;
    }

    public void setTemperature(TemperatureDTO temperature) {
        Temperature = temperature;
    }

}
