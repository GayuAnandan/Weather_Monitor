package com.java.weather.demo.repository;

import com.java.weather.demo.model.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {

}
