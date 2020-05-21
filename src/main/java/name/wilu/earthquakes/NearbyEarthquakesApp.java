package name.wilu.earthquakes;

import java.util.Optional;

public class NearbyEarthquakesApp {
    //
    public static void main(String[] args) {
        System.out.println("############## NEARBY-EARTHQUAKES ##############");
        //
        PropsProvider props = new PropsProvider();
        CachingEarthquakesDataProvider dataProvider = new CachingEarthquakesDataProvider(new ApiEarthquakesDataProvider(props));
        while (true) {
            Optional<Coordinates> maybe = new InputController().read();
            if (maybe.isPresent()) {
                System.out.println("Calculating...");
                new NearbyEarthquakes(
                        maybe.get(),
                        dataProvider,
                        props
                ).calculate().forEach(eq -> System.out.println(eq.print()));
            } else break;
        }
    }
}