package com.java.weather.demo;

import com.java.weather.demo.model.DailySummary;
import com.java.weather.demo.model.WeatherData;
import com.java.weather.demo.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DailySummaryTest {
    @InjectMocks
    private WeatherService weatherService;

    @Test
    void testDailyWeatherSummary() {
        // Simulate multiple weather data points
        List<WeatherData> weatherDataList = List.of(
                new WeatherData(30, "Clear"),
                new WeatherData(32, "Clear"),
                new WeatherData(28, "Cloudy")
        );

        weatherService.aggregateDailyWeather(weatherDataList);

        DailySummary summary = weatherService.dailySummaryRepository.findByDate(LocalDate.now());

        assertNotNull(summary);
        assertEquals(30.0, summary.getAverageTemperature(), 0.01);
        assertEquals(32.0, summary.getMaxTemperature(), 0.01);
        assertEquals(28.0, summary.getMinTemperature(), 0.01);
        assertEquals("Clear", summary.getDominantWeatherCondition());
    }
}
