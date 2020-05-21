package name.wilu.earthquakes;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NearbyEarthquakesCalculationTest {

    @Test
    void shouldCalculateResults() {
        Coordinates refPoint = new Coordinates(19.944544f, 50.049683f); // Krakow
        NearbyEarthquakes sut = new NearbyEarthquakes(
                refPoint,
                null,
                new PropsProvider()
        );
        //
        List<MeasuredEarthquake> calculate = sut.calculate(3, refPoint, earthquakes());
        assertEquals(3, calculate.size());
        Iterator<MeasuredEarthquake> eqi = calculate.iterator();
        assertEquals("Katowice", eqi.next().earthquake.title);
        assertEquals("Poznan", eqi.next().earthquake.title);
        assertEquals("Berlin", eqi.next().earthquake.title);
    }

    private List<Earthquake> earthquakes() {
        return asList(
                new Earthquake(new Coordinates(19.039993f, 50.270908f), "Katowice"),
                new Earthquake(new Coordinates(19.039993f, 50.270908f), "Katowice"),
                new Earthquake(new Coordinates(16.931992f, 52.409538f), "Poznan"),
                new Earthquake(new Coordinates(13.404954f, 52.520008f), "Berlin"),
                new Earthquake(new Coordinates(37.618423f, 55.751244f), "Moskwa"),
                new Earthquake(new Coordinates(-120.740135f, 47.751076f), "Waszyngton")
        );
    }
}