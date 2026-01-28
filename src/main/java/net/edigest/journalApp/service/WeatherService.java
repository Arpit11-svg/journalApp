package net.edigest.journalApp.service;

import net.edigest.journalApp.api.response.WeatherResponse;
import net.edigest.journalApp.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;   //MAINTAIN IT later

//    private static final String API="http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";
// Now we fetch this API from DB with the help of AppCache

    @Autowired
    private AppCache appCacheObject;

    @Autowired
    private RestTemplate restTemplate;  // Spring provided http request processor class

    public WeatherResponse getWeather(String city){
        String finalAPI=appCacheObject.appCache.get("weather_api").replace("<city>",city).replace("<API_KEY>",apiKey);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.POST, null, WeatherResponse.class);
//        we have converted incoming JSON into WeatherResponse.class(POJO)
        WeatherResponse body = response.getBody();
        return body;
    }
}
