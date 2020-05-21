package name.wilu.earthquakes;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
class NearbyEarthquakes {
    //
    private final Coordinates coordinates;
    private final EarthquakesDataProvider provider;
    private final PropsProvider props;

    List<MeasuredEarthquake> calculate() {
        List<Earthquake> all = provider.earthquakes();
        return calculate(Integer.parseInt(props.get("top.closest.earthquakes")), coordinates, all);
    }

    List<MeasuredEarthquake> calculate(int top, Coordinates refPoint, List<Earthquake> earthquakes) {
        return earthquakes
                .stream()
                .map(it -> new MeasuredEarthquake(it, refPoint))
                .sorted()
                .distinct()
                .limit(top)
                .collect(toList());
    }
}

class MeasuredEarthquake implements Comparable<MeasuredEarthquake> {
    //
    final Earthquake earthquake;
    final long distance;
    //
    MeasuredEarthquake(Earthquake earthquake, Coordinates refPoint) {
        this.earthquake = earthquake;
        this.distance = refPoint.distanceTo(
                new Coordinates(earthquake.coordinates.longitude, earthquake.coordinates.latitude)
        );
    }

    @Override public int compareTo(MeasuredEarthquake other) {
        return Long.compare(distance, other.distance);
    }

    @Override public boolean equals(Object o) {
        if (!(o instanceof MeasuredEarthquake)) return false;
        MeasuredEarthquake other = (MeasuredEarthquake) o;
        return earthquake.coordinates.longitude == other.earthquake.coordinates.longitude &&
                earthquake.coordinates.latitude == other.earthquake.coordinates.latitude;
    }

    @Override public int hashCode() {
        return Objects.hash(earthquake.coordinates.longitude, earthquake.coordinates.latitude);
    }

    public String print() {
        return earthquake.title + " || " + distance;
    }
}