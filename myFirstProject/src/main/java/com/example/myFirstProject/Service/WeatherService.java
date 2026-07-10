package com.example.myFirstProject.Service;


import com.example.myFirstProject.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {
    @Value("${weather.api.key}")
    private String apiKey;
    private static final String API = "http://api.weatherstack.com/current?access_key=YOUR_KEY&query=CITY";

    @Autowired
    private RestTemplate restTemplate;

    public String getWeather(String city) {

        String url = API.replace("CITY", city)
                .replace("YOUR_KEY", apiKey);

        ResponseEntity<WeatherResponse> response =
                restTemplate.exchange(url, HttpMethod.GET, null, WeatherResponse.class);

        WeatherResponse body = response.getBody();

        if (body != null && body.getCurrent() != null) {
            return body.getCurrent().getTemperature() + "°C, "
                    + body.getCurrent().getWeatherDescriptions().get(0);
        }

        return "Weather not available";
    }



//   this is post to api in spring boot sample code
//    public String setWeather(String city) {
//
//        String url = API.replace("CITY", city)
//                .replace("YOUR_KEY", api_key);
//        String request = "{\n" +
//                "    \"username\":\"Oye\",\n" +
//                "    \"password\":\"guddu\"\n" +
//                "}";
//        HttpEntity<String> requestEntity = new HttpEntity<>(request);
//
//        ResponseEntity<WeatherResponse> response =
//                restTemplate.exchange(url, HttpMethod.GET, requestEntity, WeatherResponse.class);
//
//        WeatherResponse body = response.getBody();
//
//        if (body != null && body.getCurrent() != null) {
//            return body.getCurrent().getTemperature() + "°C, "
//                    + body.getCurrent().getWeatherDescriptions().get(0);
//        }
//
//        return "Weather not available";
//    }
}
