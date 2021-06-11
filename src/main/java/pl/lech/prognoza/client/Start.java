package pl.lech.prognoza.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.time.*;

@Component
public class Start {

    private WeatherRepo weatherRepo;


    @Autowired
    public Start(WeatherRepo weatherRepo) {
        this.weatherRepo = weatherRepo;
    }

    @Scheduled(fixedDelay = 3600000l)
    public void init0() {
        weatherRepo.save(getWeatherForWarsaw());
    }



    private Weather getWeatherForWarsaw() {
        WeatherFact weatherFact = getWeather("Warsaw");
        Weather weather = new Weather();
        weather.setCity(weatherFact.getLocation().getName());
        weather.setRegion(weatherFact.getLocation().getRegion());
        weather.setTemperature(weatherFact.getCurrent().getTemperature());
        weather.setObservationTime(weatherFact.getCurrent().getObservationTime());
        weather.setCheckTime(LocalDateTime.now().toString());
        System.out.println(weather.toString());
        return weather;
    }

    private WeatherFact getWeather(String name) {
        RestTemplate restTemplate = new RestTemplate();
        WeatherFact weatherFact = restTemplate.getForObject("http://api.weatherstack.com/current?access_key=507c2dffac05fd7def6fff54f488828d&query=" + name, WeatherFact.class);
        System.out.println(weatherFact.toString());
        return weatherFact;
    }


}
