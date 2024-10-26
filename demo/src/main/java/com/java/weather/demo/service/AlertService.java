package com.java.weather.demo.service;

import com.java.weather.demo.model.WeatherData;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class AlertService {

    private final Map<String, Double> temperatureThresholds = new HashMap<>();
    private final Map<String, Integer> breachCounts = new HashMap<>();
    private final List<String> currentAlerts = new ArrayList<>();

    // Set thresholds for different cities
    public void setTemperatureThreshold(String city, double threshold) {
        temperatureThresholds.put(city, threshold);
        breachCounts.put(city, 0); // Initialize breach count
    }

    public void addAlert(String alert) {
        currentAlerts.add(alert);
    }
    // Method to retrieve current alerts
    public List<String> getCurrentAlerts() {
        return new ArrayList<>(currentAlerts); // Return a copy to avoid external modifications
    }

    // Optionally, a method to clear alerts
    public void clearAlerts() {
        currentAlerts.clear();
    }

    // Check if the current weather data breaches the threshold
    public void checkAlerts(WeatherData weatherData) {
        String city = weatherData.getName();
        Double threshold = temperatureThresholds.get(city);

        if (threshold != null && weatherData.getMain().getTemp() > threshold) {
            breachCounts.put(city, breachCounts.get(city) + 1);

            // Trigger alert if breached for two consecutive updates
            if (breachCounts.get(city) >= 2) {
                triggerAlert(city, weatherData);
                breachCounts.put(city, 0); // Reset count after alert
            }
        } else {
            breachCounts.put(city, 0); // Reset if below threshold
        }
    }

    // Method to trigger alert (could be extended to send emails)
    private void triggerAlert(String city, WeatherData weatherData) {
        System.out.println("Alert: Temperature in " + city + " exceeds threshold! Current Temp: " + weatherData.getMain().getTemp() + "°C");
    }
}
