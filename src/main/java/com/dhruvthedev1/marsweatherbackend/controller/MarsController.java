package com.dhruvthedev1.marsweatherbackend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhruvthedev1.marsweatherbackend.model.MarsData;
import com.dhruvthedev1.marsweatherbackend.service.MarsWeatherService;

@RestController
public class MarsController {
  private MarsWeatherService marsWeatherService;

  public MarsController(MarsWeatherService marsWeatherService) {
    this.marsWeatherService = marsWeatherService;
  }

  @GetMapping("/mars-weather")
  public List<MarsData> getMarsData() {
    return marsWeatherService.getMarsWeatherData();
  }
}
