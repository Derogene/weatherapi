package com.careerdevs.weatherapi.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/current")
public class CurrentWeatherController {

    @Autowired
    private Environment env;

    private final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    @GetMapping("/test")
    public ResponseEntity<?> testRequest (RestTemplate restTemplate) {

        try {
            String apiKey = env.getProperty("OW_API_KEY");
            String city = "providence";
            String queryString = "?q=" + city + "&appid=" + apiKey + "&units=imperial"; // key ~ ?q=, &appid; value ~ city, apiKey; ? = start of query string, & = marks a new key value pair
            String openWeatherURL = BASE_URL + queryString;

            String openWeatherResponse = restTemplate.getForObject(openWeatherURL, String.class);

            return ResponseEntity.ok(openWeatherResponse);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getClass()); // give speficity of the type of error that is thrown
            return ResponseEntity.internalServerError().body(e.getMessage());

        }
        //have a generic class that no matter data is sent, a response is granted. it allows more fine level of control over the status that is sent, data and headers in the responses

    }
}
