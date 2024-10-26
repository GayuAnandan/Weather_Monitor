package com.java.weather.demo.repository;

import com.java.weather.demo.model.DailySummary;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;

public interface DailySummaryRepository extends JpaRepository<DailySummary, LocalDate> {
    DailySummary findByDate(LocalDate date);

}
