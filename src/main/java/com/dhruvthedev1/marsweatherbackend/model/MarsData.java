package com.dhruvthedev1.marsweatherbackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MarsData {
  @JsonProperty("terrestrial_date")
  private String date;

  @JsonProperty("sol")
  private int sol;

  @JsonProperty("min_temp")
  private double low;
  
  @JsonProperty("max_Temp")
  private double high;

  public MarsData() {
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public int getSol() {
    return sol;
  }

  public void setSol(int sol) {
    this.sol = sol;
  }

  public double getLow() {
    return low;
  }

  public void setLow(double low) {
    this.low = low;
  }

  public double getHigh() {
    return high;
  }

  public void setHigh(double high) {
    this.high = high;
  }
}
