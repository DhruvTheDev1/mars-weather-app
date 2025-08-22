package com.dhruvthedev1.marsweatherbackend.service;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dhruvthedev1.marsweatherbackend.model.MarsData;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MarsWeatherService {
  // url for Mars Weather - Curiosity Rover
  private static final String urlString = "";

  public List<MarsData> getMarsWeatherData() {
    // holds all mars data
    List<MarsData> marsDataList = new ArrayList<>();
    try {
      URI uri = new URI(urlString);
      URL url = uri.toURL();
      // creates connections
      HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
      httpConnection.setRequestMethod("GET");

      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode rootNode = objectMapper.readTree(httpConnection.getInputStream());

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
