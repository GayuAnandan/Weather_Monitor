package com.java.weather.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class DailySummary {
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getAverageTemperature() {
        return averageTemperature;
    }

    public void setAverageTemperature(double averageTemperature) {
        this.averageTemperature = averageTemperature;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public String getDominantWeatherCondition() {
        return dominantWeatherCondition;
    }

    public void setDominantWeatherCondition(String dominantWeatherCondition) {
        this.dominantWeatherCondition = dominantWeatherCondition;
    }

    @Id
    private LocalDate date;
    private double averageTemperature;
    private double maxTemperature;
    private double minTemperature;
    private String dominantWeatherCondition;
}
