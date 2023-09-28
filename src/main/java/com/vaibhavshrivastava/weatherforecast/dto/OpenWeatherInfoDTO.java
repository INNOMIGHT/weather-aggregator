package com.vaibhavshrivastava.weatherforecast.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherInfoDTO {
	
	@JsonProperty("main")
    private MainData mainData;

    private int visibility;

    private WindDTO wind;

    private SysData sys;

    public MainData getMainData() {
        return mainData;
    }

    public void setMainData(MainData mainData) {
        this.mainData = mainData;
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

    public SysData getSys() {
        return sys;
    }

    public void setSys(SysData sys) {
        this.sys = sys;
    }

    
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MainData {
        @JsonProperty("feels_like")
        private double feelsLike;

        private int pressure;

        private int humidity;

        public double getFeelsLike() {
            return feelsLike;
        }

        public void setFeelsLike(double feelsLike) {
            this.feelsLike = feelsLike;
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
    }

    
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SysData {
        private long sunrise;
        private long sunset;

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
}
        

