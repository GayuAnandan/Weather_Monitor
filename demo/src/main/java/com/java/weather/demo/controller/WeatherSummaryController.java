package com.java.weather.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.java.weather.demo.model.DailySummary;
import com.java.weather.demo.service.AlertService;
import com.java.weather.demo.service.DailySummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class WeatherSummaryController {
    private final DailySummaryService dailySummaryService;
    private final AlertService alertService;

    @Autowired
    public WeatherSummaryController(DailySummaryService dailySummaryService, AlertService alertService) {
        this.dailySummaryService = dailySummaryService;
        this.alertService = alertService;
    }

    @GetMapping("/daily-summaries")
    public String getDailySummaries(Model model) {
        List<DailySummary> summaries = dailySummaryService.findAll(); // Implement this method in the service
        model.addAttribute("summaries", summaries);
        return "daily-summaries"; // Thymeleaf template name
    }

    @GetMapping("/alerts")
    public String getAlerts(Model model) {
        List<String> alerts = alertService.getCurrentAlerts(); // Implement this method in AlertService
        model.addAttribute("alerts", alerts);
        return "alerts"; // Thymeleaf template name for alerts
    }
}
