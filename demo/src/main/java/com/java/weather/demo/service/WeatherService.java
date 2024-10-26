package com.java.weather.demo.service;

import com.java.weather.demo.model.DailySummary;
import com.java.weather.demo.model.TemperatureConverter;
import com.java.weather.demo.model.TemperatureUnit;
import com.java.weather.demo.model.WeatherData;
import com.java.weather.demo.repository.DailySummaryRepository;
import com.java.weather.demo.repository.WeatherDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService {

    private final WeatherDataRepository weatherDataRepository;
    private final RestTemplate restTemplate;
    public final DailySummaryRepository dailySummaryRepository;
    private final AlertService alertService;
    private List<WeatherData> dailyWeatherData = new ArrayList<>(); // Declare dailyWeatherData here
    private LocalDate currentDay = LocalDate.now();

    @Value("${api.key}")
    private String apiKey;

    @Value("${weather.update.interval:300000}") // Default to 5 minutes (300000 ms)
    private long updateInterval;

    @Value("${temperature.unit:CELSIUS}") // Default to Celsius
    private TemperatureUnit temperatureUnit;

    @Scheduled(fixedRateString = "${weather.update.interval:300000}")
    public void fetchWeatherData() {
        // Replace "your-city" with the actual city you want to monitor
        String city = "your-city";
        WeatherData weatherData = getWeatherData(city);
        if (weatherData != null) {
            dailyWeatherData.add(weatherData); // store weather data for daily aggregation
            alertService.checkAlerts(weatherData);
            rollUpDailyWeather(); //check if we need to roll up the day for the day
        }
    }

    @Autowired
    public WeatherService(WeatherDataRepository weatherDataRepository, RestTemplate restTemplate, DailySummaryRepository dailySummaryRepository, AlertService alertService){
        this.weatherDataRepository= weatherDataRepository;
        this.restTemplate= restTemplate;
        this.dailySummaryRepository= dailySummaryRepository;
        this.alertService= alertService;
    }

    public WeatherData getWeatherData(String city){
        String url= UriComponentsBuilder.fromHttpUrl("http://api.openweathermap.org/data/2.5/weather").queryParam("q",city).queryParam("appId",apiKey).queryParam("units","metric").toUriString();
        WeatherData weatherData = restTemplate.getForObject(url, WeatherData.class);

        if(weatherData != null){
            weatherDataRepository.save(weatherData);
            alertService.checkAlerts(weatherData);
        }

        return weatherData;
    }

    private double convertTemperature(double kelvinTemp) {
        if (temperatureUnit == TemperatureUnit.CELSIUS) {
            return TemperatureConverter.kelvinToCelsius(kelvinTemp);
        } else {
            return TemperatureConverter.kelvinToFahrenheit(kelvinTemp);
        }
    }

    private void rollUpDailyWeather() {
        LocalDate today = LocalDate.now();
        if (!today.equals(currentDay)) {
            // New day, aggregate and save the previous day's data
            aggregateDailyWeather(dailyWeatherData);
            dailyWeatherData.clear(); // Clear the list for the new day
            currentDay = today; // Update to the current day
        }
    }


    public void aggregateDailyWeather(List<WeatherData> weatherDataList){
        if(weatherDataList.isEmpty()){
            return;
        }

        double totalTemp=0;
        double maxTemp= Double.MIN_VALUE;
        double minTemp= Double.MAX_VALUE;
        String dominantCondition= "";
        int conditionCount=0;

        for(WeatherData weatherData :weatherDataList){
            double temp= weatherData.getMain().getTemp();
            totalTemp += temp;
            if(temp>maxTemp) maxTemp=temp;
            if(temp<minTemp) minTemp=temp;

            String condition = weatherData.getWeather().get(0).getDescription();
            if (conditionCount == 0 || condition.equals(dominantCondition)) {
                dominantCondition = condition;
                conditionCount++;
            }
        }

        double averageTemp = totalTemp / weatherDataList.size();
        DailySummary summary = new DailySummary();
        summary.setDate(LocalDate.now());
        summary.setAverageTemperature(averageTemp);
        summary.setMaxTemperature(maxTemp);
        summary.setMinTemperature(minTemp);
        summary.setDominantWeatherCondition(dominantCondition);

        dailySummaryRepository.save(summary);
    }

    public void checkAlerts(WeatherData weatherData) {
        double temp = weatherData.getMain().getTemp();
        if (temp > 35) { // Example threshold
            alertService.addAlert("Temperature exceeded 35°C in " + weatherData.getName());
        }
    }

}
