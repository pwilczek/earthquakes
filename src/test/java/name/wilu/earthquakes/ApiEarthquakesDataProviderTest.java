package name.wilu.earthquakes;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ApiEarthquakesDataProviderTest {

    @Test @Disabled
    void realApiLearningTest() {
        ApiEarthquakesDataProvider sut = new ApiEarthquakesDataProvider(new PropsProvider());
        List<Earthquake> data = sut.earthquakes();
        assertEquals(9, data.size());
    }
}