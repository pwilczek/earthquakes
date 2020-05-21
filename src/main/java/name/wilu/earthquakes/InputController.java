package name.wilu.earthquakes;

import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

import static java.lang.String.join;
import static name.wilu.earthquakes.InputController.ExitCodes;

class InputController {
    //
    final static Set<String> ExitCodes = Set.of("q", "quit", "exit");
    //
    Optional<Coordinates> read() {
        System.out.println("Type [" + join(", ", ExitCodes) + "] to quit");
        ValueReader lon = new ValueReader(new StdInScanner());
        ValueReader lat = new ValueReader(new StdInScanner());
        if (lon.read(new Longitude()) && lat.read(new Latitude()))
            return Optional.of(new Coordinates(lon.val().get(), lat.val().get()));
        return Optional.empty();
    }
}


@RequiredArgsConstructor
class ValueReader {
    //
    final ScannerWrapper scanner;
    private ValueHolder val;
    //
    boolean read(ValueHolder holder) {
        while (true) {
            System.out.println("Please provide " + holder.type() + " value");
            String read = scanner.next();
            if (isExitCode(read)) {
                System.out.println("Quiting...");
                return false;
            }
            try {
                float maybe = Float.parseFloat(read);
                if (holder.isValid(maybe)) {
                    val = holder.put(maybe);
                    System.out.println("Read " + holder.type() + " " + val.get());
                    return true;
                } else System.out.println(holder.type() + " value " + read + " is not correct, try again...");
            } catch (Throwable t) {
                System.out.println(holder.type() + " value " + read + " is not correct, try again...");
            }
        }
    }

    ValueHolder val() {
        return val;
    }

    private boolean isExitCode(String val) {
        return ExitCodes.stream().anyMatch(it -> it.toUpperCase().equals(val.toUpperCase()));
    }
}

interface ScannerWrapper {
    String next();
}

class StdInScanner implements ScannerWrapper {
    //
    final Scanner scanner = new Scanner(System.in);
    //
    @Override public String next() {
        return scanner.next();
    }
}