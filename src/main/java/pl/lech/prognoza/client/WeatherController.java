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
import java.util.List;

@Controller
public class WeatherController {

    List<String> citiesList = new ArrayList<>();

    public WeatherController() {
        citiesList.add("Warsaw");
        citiesList.add("Berlin");
        citiesList.add("Oslo");
        citiesList.add("Paris");
        citiesList.add("Moscow");
        citiesList.add("Brasilia");

    }

    private WeatherFact getWeather(String name) {
        RestTemplate restTemplate = new RestTemplate();
        WeatherFact weatherFact = restTemplate.getForObject("http://api.weatherstack.com/current?access_key=507c2dffac05fd7def6fff54f488828d&query=" + name, WeatherFact.class);
        System.out.println(weatherFact.toString());
        return weatherFact;
    }

    @GetMapping("/city")
    public String createForm(Model model) {
//        model.addAttribute("cities", citiesList);
        model.addAttribute("city", new City());
        model.addAttribute("noCity", null);
        model.addAttribute("weatherFact", new WeatherFact());
        return "weatherView";
    }

    @PostMapping("/city")
    public String showWeather(@ModelAttribute City city, Model model) {
        WeatherFact weatherFact = getWeather(city.getName());
        if (weatherFact.getLocation() == null) {
            model.addAttribute("noCity", true);
        }
        model.addAttribute("weatherFact", weatherFact);
//        model.addAttribute("cities", citiesList);
        model.addAttribute("cityName", city.getName());
        return "weatherView";
    }

}
