package net.edigest.journalApp.api.response;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeatherResponse {
    private Current current;

    @Getter
    @Setter
    public static class Current{

        @JsonProperty("observation_time")
        private String observationTime;  //means data which we fetch, in this observation_time is present, but we use camel case, so use @JsonProperty so that it can understand this observationTime(for good practice) is this observation_time

        private int temperature;

        @JsonProperty("weather_descriptions")
        private List<String> weatherDescriptions;

        @JsonProperty("wind_speed")
        private int windSpeed;

        private int feelsLike;
    }

}


