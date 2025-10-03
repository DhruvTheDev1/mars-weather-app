package com.dhruvthedev1.marsweatherbackend.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dhruvthedev1.marsweatherbackend.model.MarsData;
import com.dhruvthedev1.marsweatherbackend.service.MarsWeatherService;

@Controller
public class MarsController {
  private MarsWeatherService marsWeatherService;

  public MarsController(MarsWeatherService marsWeatherService) {
    this.marsWeatherService = marsWeatherService;
  }

  @GetMapping("/mars-weather")
  public String getMarsData(Model model) {
    List<MarsData> marsData = marsWeatherService.getMarsWeatherData();
    model.addAttribute("marsData", marsData);
    return "mars-weather";
  }
}
