package name.wilu.earthquakes;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NearbyEarthquakesTest {

    @Test
    void shouldCalculateEmpty() {
        TestEarthquakesDataProvider provider = earthquakesProvider();
        NearbyEarthquakes nearbyEarthquakes = new NearbyEarthquakes(new Coordinates(0f,0f), provider, new PropsProvider());
        assertEquals(0, nearbyEarthquakes.calculate().size());
    }

    @Test
    void shouldReturnWhatIsProvided() {
        TestEarthquakesDataProvider provider = earthquakesProvider(
                new Earthquake(new Coordinates(0f, 0f), "test")
        );
        NearbyEarthquakes nearbyEarthquakes = new NearbyEarthquakes(new Coordinates(0f,0f), provider, new PropsProvider());
        assertEquals(1, nearbyEarthquakes.calculate().size());
    }

    private TestEarthquakesDataProvider earthquakesProvider(Earthquake... earthquakes) {
        return new TestEarthquakesDataProvider(asList(earthquakes));
    }
}

@RequiredArgsConstructor
class TestEarthquakesDataProvider implements EarthquakesDataProvider {
    //
    private final List<Earthquake> earthquakes;
    //
    @Override public List<Earthquake> earthquakes() {
        return earthquakes;
    }
}