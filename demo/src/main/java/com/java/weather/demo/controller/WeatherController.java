package com.java.weather.demo.controller;

import com.java.weather.demo.model.WeatherData;
//import lombok.Value;
import com.java.weather.demo.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

@Controller
public class WeatherController {

    @Value("${api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    @Autowired
    public WeatherController(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }

    @GetMapping("/")
    public String getIndex(){
        return "index";
    }

    @GetMapping("/weather")
    public String getWeather(@RequestParam("city") String city, Model model){
        String url="http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appId=" + apiKey +"&units=metric";
        //RestTemplate restTemplate=new RestTemplate();
        WeatherData weatherData = restTemplate.getForObject(url,WeatherData.class);
        //WeatherData weatherData = weatherService.getWeatherData(city);

        if(weatherData != null){
            model.addAttribute("city",weatherData.getName());
            model.addAttribute("country", weatherData.getSys().getCountry());
            model.addAttribute("weatherDescription", weatherData.getWeather().get(0).getDescription());
            model.addAttribute("temperature", weatherData.getMain().getTemp());
            model.addAttribute("feels", weatherData.getMain().getFeels());
            model.addAttribute("humidity", weatherData.getMain().getHumidity());
            model.addAttribute("windSpeed", weatherData.getWind().getSpeed());
            model.addAttribute("updateTime", weatherData.getDt());
            String weatherIcon= "wi wi-owm-" + weatherData.getWeather().get(0).getId();
            model.addAttribute("weatherIcon", weatherIcon);
        } else{
            model.addAttribute("error","City not found.");
        }

        return "weather";
    }
}
