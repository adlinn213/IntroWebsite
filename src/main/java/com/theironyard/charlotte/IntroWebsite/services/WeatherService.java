package com.theironyard.charlotte.IntroWebsite.services;

import com.theironyard.charlotte.IntroWebsite.models.WeatherData;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class WeatherService {
    RestTemplate template;

    public WeatherService(RestTemplate template) {
        this.template = template;
    }

    public WeatherData getWeather() {
        Map<String, Object> id = new HashMap<>();
        id.put("myID", (String.format("%s", System.getenv("DSKY_SKEY"))));

        HttpEntity<WeatherData> response = template.exchange("https://api.darksky.net/forecast/{myID}" +
                "/35.2271,80.8431", HttpMethod.GET, null, WeatherData.class, id);

        return response.getBody();
    }
}
