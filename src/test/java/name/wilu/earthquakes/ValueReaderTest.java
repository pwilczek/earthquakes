package name.wilu.earthquakes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Iterator;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;

class ValueReaderTest {

    @Test
    void shouldReadCorrectCoordinate() {
        float f = 42.3f;
        ValueReader sut = new ValueReader(new TestScanner(Float.toString(f)));
        sut.read(TestValHolder.I);
        assertEquals(f, sut.val().get());
        //
        sut = new ValueReader(new TestScanner(Float.toString(-f)));
        sut.read(TestValHolder.I);
        assertEquals(-f, sut.val().get());
    }

    @ParameterizedTest
    @ValueSource(strings = {"q", "quit", "exit"})
    void shouldTerminateOnExitCode(String code) {
        ValueReader sut = new ValueReader(new TestScanner(code));
        assertFalse(sut.read(TestValHolder.I));
    }

    @ParameterizedTest
    @ValueSource(floats = {-90, -45.5f, 0, 45.5f, 90})
    void shouldCheckLats(float val) {
        ValueReader sut = new ValueReader(new TestScanner(Float.toString(val)));
        sut.read(new Latitude());
        assertEquals(val, sut.val().get());
    }

    @ParameterizedTest
    @ValueSource(floats = {-91, 91, 370})
    void shouldFailOnBadLats(float val) {
        ValueReader sut = new ValueReader(new TestScanner(Float.toString(val), "q"));
        sut.read(new Latitude());
        assertNull(sut.val());
    }

    @ParameterizedTest
    @ValueSource(floats = {-180, -90.5f, 0, 90.5f, 180})
    void shouldCheckLongs(float val) {
        ValueReader sut = new ValueReader(new TestScanner(Float.toString(val)));
        sut.read(new Longitude());
        assertEquals(val, sut.val().get());
    }

    @ParameterizedTest
    @ValueSource(floats = {-181, 181, 370})
    void shouldFailOnBadLongs(float val) {
        ValueReader sut = new ValueReader(new TestScanner(Float.toString(val), "q"));
        sut.read(new Longitude());
        assertNull(sut.val());
    }
}

class TestValHolder extends ValueHolder {
    //
    static TestValHolder I = new TestValHolder();
    //
    @Override boolean isValid(float f) {
        return true;
    }

    @Override String type() {
        return "in test";
    }
}

class TestScanner implements ScannerWrapper {
    //
    private final Iterator<String> scanned;
    //
    TestScanner(String...scanned) {
        this.scanned = stream(scanned).collect(toList()).iterator();
    }
    //
    @Override public String next() {
        return scanned.next();
    }
}