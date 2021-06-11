package pl.lech.prognoza.client;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "wheather")
public class Weather {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String checkTime;
  private String city;
  private String region;
  private long temperature;
  private String observationTime;

  public Weather() {
  }

  public Weather(String checkTime, String city, String region, long temperature, String observationTime) {
    this.checkTime = checkTime;
    this.city = city;
    this.region = region;
    this.temperature = temperature;
    this.observationTime = observationTime;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getCheckTime() {
    return checkTime;
  }

  public void setCheckTime(String checkTime) {
    this.checkTime = checkTime;
  }


  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }


  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }


  public long getTemperature() {
    return temperature;
  }

  public void setTemperature(long temperature) {
    this.temperature = temperature;
  }


  public String getObservationTime() {
    return observationTime;
  }

  public void setObservationTime(String observationTime) {
    this.observationTime = observationTime;
  }

  @Override
  public String toString() {
    return "Weather{" +
            "id=" + id +
            ", checkTime=" + checkTime +
            ", city='" + city + '\'' +
            ", region='" + region + '\'' +
            ", temperature=" + temperature +
            ", observationTime='" + observationTime + '\'' +
            '}';
  }
}
