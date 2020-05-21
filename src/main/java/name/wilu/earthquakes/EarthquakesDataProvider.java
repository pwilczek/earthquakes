package name.wilu.earthquakes;

import feign.Feign;
import feign.RequestLine;
import feign.gson.GsonDecoder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
class ApiEarthquakesDataProvider implements EarthquakesDataProvider {
    //
    final PropsProvider props;
    //
    @Override public List<Earthquake> earthquakes() {
        return Feign.builder()
                .decoder(new GsonDecoder())
                .target(EarthquakeHazardsApi.class, props.get("earthquake.hazards.host"))
                .earthquakes()
                .features.stream().map(it -> {
                    float[] coordinates = it.geometry.coordinates;
                    return new Earthquake(
                            new Coordinates(coordinates[0], coordinates[1]),
                            it.properties.title
                    );
                }).collect(toList());
    }
}

interface EarthquakeHazardsApi {
//    @RequestLine("GET /earthquakes/feed/v1.0/summary/4.5_day.geojson")
    @RequestLine("GET /earthquakes/feed/v1.0/summary/all_month.geojson")
    ApiResponse earthquakes();
}

interface EarthquakesDataProvider {
    List<Earthquake> earthquakes();
}

@Data class ApiResponse {
    List<Features> features;
}

@Data class Features {
    Properties properties;
    Geometry geometry;
}

@Data class Geometry {
    float[] coordinates;
}

@Data class Properties {
    String title;
}