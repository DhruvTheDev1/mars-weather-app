package com.dhruvthedev1.marsweatherbackend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;

import com.dhruvthedev1.marsweatherbackend.model.MarsData;
import com.dhruvthedev1.marsweatherbackend.service.MarsWeatherService;

import reactor.core.publisher.Mono;

import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import org.springframework.web.reactive.function.client.WebClient;

@ExtendWith(MockitoExtension.class)
public class MarsWeatherServiceTest {

  @Mock
  private WebClient webClient;
  @SuppressWarnings("rawtypes")
  @Mock
  private RequestHeadersUriSpec requestHeadersUriSpec;
  @Mock
  private ResponseSpec responseSpec;

  @InjectMocks
  // creates real instance and injects mock objects into it
  private MarsWeatherService marsWeatherService;

  @BeforeEach
  public void setUp() {
    // setting up mock chain
    when(webClient.get()).thenReturn(requestHeadersUriSpec); // returns requestHeaderUriSpec object
    when(requestHeadersUriSpec.retrieve()).thenReturn(responseSpec); // returns mocked responseSpec object
  }

  @Test
  public void TestMarsWeatherService() {
    // JSON response to be returned
    String jsonResponse = """
        {
          "soles": [
            {
              "terrestrial_date": "2025-09-27",
              "sol": 4672,
              "min_temp": -76,
              "max_temp": -10
            },
            {
              "terrestrial_date": "2025-09-26",
              "sol": 4671,
              "min_temp": -76,
              "max_temp": -16
            },
            {
              "terrestrial_date": "2025-09-25",
              "sol": 4670,
              "min_temp": -77,
              "max_temp": -13
            },
            {
              "terrestrial_date": "2025-09-24",
              "sol": 4669,
              "min_temp": -76,
              "max_temp": -20
            },
            {
              "terrestrial_date": "2025-09-23",
              "sol": 4668,
              "min_temp": -75,
              "max_temp": -13
            },
            {
              "terrestrial_date": "2025-09-22",
              "sol": 4667,
              "min_temp": -76,
              "max_temp": -11
            },
            {
              "terrestrial_date": "2025-09-21",
              "sol": 4666,
              "min_temp": -76,
              "max_temp": -14
            }
          ]
        }
        """;
    // mocking the API response
    // returns a Mono containing the jsonResponse data
    when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just(jsonResponse));

    // calls the service method
    // triggers the chain of mock behaviour and uses jsonResponse data
    List<MarsData> testMarsData = marsWeatherService.getMarsWeatherData();

    // verifies list size
    assertEquals(7, testMarsData.size());

    // verifies first json response
    MarsData firstTestData = testMarsData.get(0);
    assertEquals("2025-09-27", firstTestData.getDate());
    assertEquals(4672, firstTestData.getSol());
    assertEquals(-76, firstTestData.getLow());
    assertEquals(-10, firstTestData.getHigh());

  }

}
