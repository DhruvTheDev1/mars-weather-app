package com.dhruvthedev1.marsweatherbackend.service;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.dhruvthedev1.marsweatherbackend.model.MarsData;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MarsWeatherService {
  private static final String urlString = "";

  public List<MarsData> getMarsWeatherData() {
        List<MarsData> marsDataList = new ArrayList<>();
    try {
      URI uri = new URI(urlString);
      URL url = uri.toURL();

      HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
      httpConnection.setRequestMethod("GET");

      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode rootNode = objectMapper.readTree(httpConnection.getInputStream());

      JsonNode solesNode = rootNode.get("soles");

      for (JsonNode solNode : solesNode) {
        String date = solNode.get("terrestrial_date").asText();
        int sol = solNode.get("sol").asInt();
        double low = solNode.get("min_temp").asDouble();
        double high = solNode.get("max_temp").asDouble();

        // Create MarsData object and add it to the list
        MarsData marsData = new MarsData(date, sol, low, high);
        marsDataList.add(marsData);
      }

    } catch (Exception e) {
      e.printStackTrace();;
    }

    return marsDataList;

  }
    public static void main(String[] args) {
        MarsWeatherService service = new MarsWeatherService();
        List<MarsData> weatherData = service.getMarsWeatherData();

        // Print first 5 entries of weather data
        for (int i = 0; i < 5 && i < weatherData.size(); i++) {
            System.out.println(weatherData.get(i));
        }
    }


}
