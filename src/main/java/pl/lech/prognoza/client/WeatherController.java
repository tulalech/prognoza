package pl.lech.prognoza.client;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import pl.lech.prognoza.client.WeatherFact;

import java.util.ArrayList;

@Controller
public class WeatherController {

    ArrayList<String> citiesList = new ArrayList<>();

    public WeatherController() {
        citiesList.add(0, "Warsaw");
        citiesList.add(1, "Berlin");
        citiesList.add(2, "Oslo");
        citiesList.add(3, "Paris");
        citiesList.add(4, "Moscow");

    }

    private WeatherFact getWeather(String name) {
        RestTemplate restTemplate = new RestTemplate();
        WeatherFact weatherFact = restTemplate.getForObject("http://api.weatherstack.com/current?access_key=507c2dffac05fd7def6fff54f488828d&query=" + name, WeatherFact.class);
        System.out.println(weatherFact.toString());
        return weatherFact;
    }

    @GetMapping("/city")
    public String createForm(Model model) {
        model.addAttribute("cities", citiesList);
        model.addAttribute("cityName", new City());
        model.addAttribute("weatherFact", new WeatherFact());
        return "weatherView";
    }

    @PostMapping("/city")
    public String showWeather(Model model, @ModelAttribute City city) {
        WeatherFact weatherFact = getWeather(city.getName());
        model.addAttribute("weatherFact", weatherFact);
        model.addAttribute("cities", citiesList);
        model.addAttribute("cityName", city);
        return "weatherView";
    }

}
