package com.dhruvthedev1.marsweatherbackend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
  // url for Mars Weather - Curiosity Rover
  @Value("${url}")
  private String urlString;

  @Bean
  public WebClient webClient() {
    return WebClient.builder()
        .baseUrl(urlString)
        .exchangeStrategies(ExchangeStrategies
            .builder()
            .codecs(codecs -> codecs
                .defaultCodecs()
                .maxInMemorySize(2 * 1024 * 1024))
            .build())
        .build();
  }
}
