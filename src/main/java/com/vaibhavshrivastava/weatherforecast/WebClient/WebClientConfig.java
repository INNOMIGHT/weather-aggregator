package com.vaibhavshrivastava.weatherforecast.WebClient;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;



import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;

import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebClientConfig {

    @Value("https://dataservice.accuweather.com/locations/v1/cities")
    private String accuweatherCityApiUrl;

    @Value("https://dataservice.accuweather.com/currentconditions/v1")
    private String accuweatherConditionApiUrl;

    @Value("https://api.openweathermap.org/geo/1.0")
    private String openWeatherLocationApiUrl;
    
    @Value("https://api.openweathermap.org/data/2.5/")
    private String openWeatherInfoApiUrl;
    
    @Value("2000")
    private int api1ConnectionTimeout;

    @Value("2000")
    private int api1ReadTimeout;

    @Value("2000")
    private int api1WriteTimeout;

    @Value("1000")
    private int api2ConnectionTimeout;

    @Value("1000")
    private int api2ReadTimeout;

    @Value("1000")
    private int api2WriteTimeout;

    @Value("1000")
    private int api3ConnectionTimeout;

    @Value("1000")
    private int api3ReadTimeout;

    @Value("1000")
    private int api3WriteTimeout;
    
    @Value("1000")
    private int api4ConnectionTimeout;

    @Value("1000")
    private int api4ReadTimeout;

    @Value("1000")
    private int api4WriteTimeout;

    @Bean("accuweatherCityApi1WebClient")
    public WebClient webClientForApi1() {
        return WebClient.builder()
                .baseUrl(accuweatherCityApiUrl)
                .clientConnector(new ReactorClientHttpConnector(HttpClient.newConnection()
                        .compress(true)
                        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, api1ConnectionTimeout)
                        .responseTimeout(Duration.ofMillis(api1ReadTimeout))
                        .doOnConnected(conn ->
                                conn.addHandlerLast(new ReadTimeoutHandler(api1ReadTimeout, TimeUnit.MILLISECONDS))
                                        .addHandlerLast(new WriteTimeoutHandler(api1WriteTimeout, TimeUnit.MILLISECONDS)))))
                .build();
    }

    @Bean("accuweatherConditionApi2WebClient")
    public WebClient webClientForApi2() {
        // Similar configuration for API2 with different timeouts
    	return WebClient.builder()
                .baseUrl(accuweatherConditionApiUrl)
                .clientConnector(new ReactorClientHttpConnector(HttpClient.newConnection()
                        .compress(true)
                        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, api2ConnectionTimeout)
                        .responseTimeout(Duration.ofMillis(api2ReadTimeout))
                        .doOnConnected(conn ->
                                conn.addHandlerLast(new ReadTimeoutHandler(api2ReadTimeout, TimeUnit.MILLISECONDS))
                                        .addHandlerLast(new WriteTimeoutHandler(api2WriteTimeout, TimeUnit.MILLISECONDS)))))
                .build();
    }

    @Bean("openWeatherLocationApi3WebClient")
    public WebClient webClientForApi3() {
        // Similar configuration for API3 with different timeouts
    	return WebClient.builder()
                .baseUrl(openWeatherLocationApiUrl)
                .clientConnector(new ReactorClientHttpConnector(HttpClient.newConnection()
                        .compress(true)
                        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, api3ConnectionTimeout)
                        .responseTimeout(Duration.ofMillis(api3ReadTimeout))
                        .doOnConnected(conn ->
                                conn.addHandlerLast(new ReadTimeoutHandler(api3ReadTimeout, TimeUnit.MILLISECONDS))
                                        .addHandlerLast(new WriteTimeoutHandler(api3WriteTimeout, TimeUnit.MILLISECONDS)))))
                .build();
    }
    
    @Bean("openWeatherInfoApi4WebClient")
    public WebClient webClientForApi4() {
        // Similar configuration for API3 with different timeouts
    	return WebClient.builder()
                .baseUrl(openWeatherInfoApiUrl)
                .clientConnector(new ReactorClientHttpConnector(HttpClient.newConnection()
                        .compress(true)
                        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, api3ConnectionTimeout)
                        .responseTimeout(Duration.ofMillis(api4ReadTimeout))
                        .doOnConnected(conn ->
                                conn.addHandlerLast(new ReadTimeoutHandler(api4ReadTimeout, TimeUnit.MILLISECONDS))
                                        .addHandlerLast(new WriteTimeoutHandler(api4WriteTimeout, TimeUnit.MILLISECONDS)))))
                .build();
    }
}

//@Service
//public class WebClientConfig {
//	
//	private final WebClient webClient1;
//	
//	public WebClientConfig(WebClient.Builder webClientBuilder) {
//		webClient1 = webClientBuilder.baseUrl("https://dataservice.accuweather.com/locations/v1").build();
//
//	}
//	
//	
//	public AccuweatherCity[] getLocationKey(String city) {
//		AccuweatherCity[] locationkey = webClient1.get()
//				.uri("/search?q={city}&apikey={key}", city, "9XLd9Je0zc6YL6x362I3ivGXI7bIAuef")
//				.retrieve()
//				.bodyToMono(AccuweatherCity[].class)
//				.block();
//		
//		
//	return locationkey;
//}
//}
