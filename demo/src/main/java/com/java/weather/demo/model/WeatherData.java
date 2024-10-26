package com.java.weather.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.*;

@Getter
@Setter
@Entity
public class WeatherData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String name;
    private long dt;
    @Embedded
    private Sys sys;
    @Embedded
    private Wind wind;
    @ElementCollection
    private List<Weather> weather;
    @Embedded
    private Main main;

    private double temperature;
    private String condition;

    public WeatherData() {
    }

    public WeatherData(int temperature, String condition) {
        this.temperature = temperature;
        this.condition = condition;
    }

    @Setter
    @Getter
    public static class Sys{
        private String country;
    }

    @Setter
    @Getter
    @Embeddable
    public static class Weather{
        private int id;
        private String description;
    }

    @Setter
    @Getter
    public static class Main{
        private double temp;
        private double feels;
        private int humidity;
    }

    @Setter
    @Getter
    public static class Wind{
        private double speed;
    }

}
