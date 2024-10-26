package com.java.weather.demo;

import com.java.weather.demo.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
class WeatherMonitoringApplicationTests {

	@Autowired
	private WeatherService weatherService;

	@Test
	void contextLoads() {
		assertNotNull(weatherService);
	}

}
