package name.wilu.earthquakes;

import java.util.ArrayList;
import java.util.List;

class CachingEarthquakesDataProvider implements EarthquakesDataProvider {
    //
    private final EarthquakesDataProvider provider;
    private final List<Earthquake> cached = new ArrayList<>();
    //
    CachingEarthquakesDataProvider(EarthquakesDataProvider provider) {
        this.provider = provider;
    }

    @Override public List<Earthquake> earthquakes() {
        if (cached.isEmpty()) cached.addAll(provider.earthquakes());
        return cached;
    }
}