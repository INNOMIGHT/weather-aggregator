package com.vaibhavshrivastava.weatherforecast.dto;

public class AccuweatherDTO {
	
	private String WeatherText;
    private boolean HasPrecipitation;
    private String PrecipitationType;
    private boolean IsDayTime;
    private TemperatureDTO Temperature;
    // Getters and setters for the fields
    public String getWeatherText() {
        return WeatherText;
    }

    public void setWeatherText(String weatherText) {
        WeatherText = weatherText;
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
