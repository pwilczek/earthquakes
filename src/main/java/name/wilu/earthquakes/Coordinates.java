package name.wilu.earthquakes;

import lombok.RequiredArgsConstructor;
import org.geotools.referencing.GeodeticCalculator;

@RequiredArgsConstructor
class Coordinates {
    //
    final float longitude;
    final float latitude;
    //
    long distanceTo(Coordinates to) {
        GeodeticCalculator gc = new GeodeticCalculator();
        gc.setStartingGeographicPoint(longitude, latitude);
        gc.setDestinationGeographicPoint(to.longitude, to.latitude);
        return Math.round(gc.getOrthodromicDistance() / 1000);
    }
}

class Latitude extends ValueHolder {
    //
    @Override public boolean isValid(float f) {
        return f >= -90 && f <= 90;
    }

    @Override String type() {
        return "latitude";
    }
}

class Longitude extends ValueHolder {
    //
    @Override public boolean isValid(float f) {
        return f >= -180 && f <= 180;
    }

    @Override String type() {
        return "longitude";
    }
}

abstract class ValueHolder {
    //
    private float val;
    //
    abstract boolean isValid(float f);
    abstract String type();
    //
    float get() {
        return val;
    }

    ValueHolder put(float val) {
        this.val = val;
        return this;
    }
}