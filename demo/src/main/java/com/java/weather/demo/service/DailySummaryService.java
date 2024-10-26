package com.java.weather.demo.service;

import com.java.weather.demo.model.DailySummary;
import com.java.weather.demo.repository.DailySummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailySummaryService {
    private final DailySummaryRepository dailySummaryRepository;

    @Autowired
    public DailySummaryService(DailySummaryRepository dailySummaryRepository) {
        this.dailySummaryRepository = dailySummaryRepository;
    }

    public List<DailySummary> findAll() {
        return dailySummaryRepository.findAll(); // Ensure this method is implemented in the repository
    }
}
