package com.dhruvthedev1.marsweatherbackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MarsData {
  @JsonProperty("terrestrial_date")
  // yyyy-mm-dd
  private String date;

  @JsonProperty("sol")
  private int sol;

  @JsonProperty("min_temp")
  private double low;
  
  @JsonProperty("max_temp")
  private double high;

  public MarsData() {
  }

  public MarsData(String date, int sol, double low, double high) {
    this.date = date;
    this.sol = sol;
    this.low = low;
    this.high = high;
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

    @Override
  public String toString() {
    return "MarsData{" +
           "date='" + date + '\'' +
           ", sol=" + sol +
           ", low=" + low +
           ", high=" + high +
           '}';
  }

}
