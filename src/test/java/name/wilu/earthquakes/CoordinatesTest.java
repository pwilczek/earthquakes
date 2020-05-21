package name.wilu.earthquakes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinatesTest {

    @Test
    void distanceSelf() {
        Coordinates coordinates = new Coordinates(0f, 0f);
        assertEquals(0, coordinates.distanceTo(coordinates));
    }

    @Test
    void distanceKrakowPoznan() {
        Coordinates krk = new Coordinates(19.944544f, 	50.049683f);
        Coordinates pzn = new Coordinates(16.931992f, 52.409538f);
        assertEquals(krk.distanceTo(pzn), pzn.distanceTo(krk));
        assertEquals(336, krk.distanceTo(pzn));
    }

    @Test
    void distanceNorthSouthPoles() {
        Coordinates north = new Coordinates(0f, 0f);
        Coordinates south = new Coordinates(0f, 90f);
        assertEquals((long) Math.round(1.0001966E7/1000), north.distanceTo(south));;
    }
}