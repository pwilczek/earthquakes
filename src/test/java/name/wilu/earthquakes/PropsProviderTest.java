package name.wilu.earthquakes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PropsProviderTest {

    @Test
    void shouldProvidePropVal() {
        assertEquals("10", new PropsProvider().get("top.closest.earthquakes"));
    }
}