package com.dhruvthedev1.marsweatherbackend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.dhruvthedev1.marsweatherbackend.model.MarsData;

public class MarsWeatherServiceTest {

  @Test
  public void TestGetMarsWeather() {
    // test data
    MarsData testData = new MarsData("2025-09-21", 4666, -76, -14);
    List<MarsData> list = new ArrayList<>();
    list.add(testData);

    assertEquals(1, list.size());

    MarsData firstData = list.get(0);
    assertEquals("2025-09-21", firstData.getDate());
    assertEquals(4666, firstData.getSol());
    assertEquals(-76, firstData.getLow());
    assertEquals(-14, firstData.getHigh());

  }

}
