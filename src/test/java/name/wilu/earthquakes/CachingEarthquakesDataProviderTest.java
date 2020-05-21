package name.wilu.earthquakes;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CachingEarthquakesDataProviderTest {

    @Test
    void shouldReadOnceAndCache() {
        EarthquakesDataProvider dp = Mockito.mock(EarthquakesDataProvider.class);
        when(dp.earthquakes()).thenReturn(asList(
                new Earthquake(new Coordinates(1f, 2f), "mocked")
        ));
        CachingEarthquakesDataProvider sut = new CachingEarthquakesDataProvider(dp);
        assertEquals(1, sut.earthquakes().size());
        assertEquals(1, sut.earthquakes().size());
        verify(dp, Mockito.times(1)).earthquakes();
    }
}