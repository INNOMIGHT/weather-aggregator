package com.vaibhavshrivastava.weatherforecast.dto;


public class TemperatureDTO {
    private double Value;
    private String Unit;

    // Getters and setters for the fields
    public double getValue() {
        return Value;
    }

    public void setValue(double value) {
        Value = value;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

}