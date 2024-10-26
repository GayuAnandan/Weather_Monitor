package com.java.weather.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TemperatureConversionTest {
    @Test
    void testKelvinToCelsius() {
        double kelvin = 300;
        double expectedCelsius = kelvin - 273.15;
        assertEquals(expectedCelsius, convertKelvinToCelsius(kelvin), 0.01);
    }

    @Test
    void testKelvinToFahrenheit() {
        double kelvin = 300;
        double expectedFahrenheit = (kelvin - 273.15) * 9/5 + 32;
        assertEquals(expectedFahrenheit, convertKelvinToFahrenheit(kelvin), 0.01);
    }

    // Implement conversion methods for testing
    private double convertKelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    private double convertKelvinToFahrenheit(double kelvin) {
        return (kelvin - 273.15) * 9/5 + 32;
    }
}
