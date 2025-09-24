package com.dhruvthedev1.marsweatherbackend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.dhruvthedev1.marsweatherbackend.model.MarsData;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Mono;

@Service
public class MarsWeatherService {
  private WebClient webClient;

  public MarsWeatherService(WebClient webClient) {
    this.webClient = webClient; // injects the webClientConfig
  }

  public List<MarsData> getMarsWeatherData() {
    // holds all mars data
    List<MarsData> marsDataList = new ArrayList<>();
    try {
      // initiates a GET request
      // expects a string as the response -> bodyToMono(String.class) -> converts
      // response to Mono<String>
      Mono<String> response = webClient.get().retrieve().bodyToMono(String.class);

      // makes it synchronous as it blocks the thread
      // waits until response is recieved
      String responseBody = response.block();

      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode rootNode = objectMapper.readTree(responseBody);

      // within the JSON array soles is the weather data
      JsonNode solesNode = rootNode.get("soles");

      for (int i = 0; i < 7; i++) {
        JsonNode solNode = solesNode.get(i);
        String date = solNode.get("terrestrial_date").asText(); // date
        int solYear = solNode.get("sol").asInt(); // solar day on mars
        double low = solNode.get("min_temp").asDouble(); // low
        double high = solNode.get("max_temp").asDouble(); // high

        // Create MarsData object and add it to the list
        MarsData marsData = new MarsData(date, solYear, low, high);
        marsDataList.add(marsData);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

    return marsDataList;
  }
}
