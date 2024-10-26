package com.java.weather.demo;

import com.java.weather.demo.model.WeatherData;
import com.java.weather.demo.repository.WeatherDataRepository;
import com.java.weather.demo.service.WeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class WeatherServiceDataRetrievalTest {
    @InjectMocks
    private WeatherService weatherService;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private WeatherDataRepository weatherDataRepository;

    private WeatherData mockWeatherData;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockWeatherData = new WeatherData(); // Set up mock data as needed
    }

    @Test
    void testFetchWeatherData() {
        String city = "Delhi";
        when(restTemplate.getForObject(anyString(), eq(WeatherData.class))).thenReturn(mockWeatherData);

        WeatherData result = weatherService.getWeatherData(city);

        assertNotNull(result);
        verify(restTemplate).getForObject(anyString(), eq(WeatherData.class));
        verify(weatherDataRepository).save(mockWeatherData);
    }
}
