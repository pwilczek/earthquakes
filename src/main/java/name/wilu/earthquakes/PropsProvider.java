package name.wilu.earthquakes;

import lombok.SneakyThrows;

import java.util.Properties;

import static org.apache.commons.lang3.StringUtils.EMPTY;

class PropsProvider {
    //
    static Properties PROPS;
    //
    @SneakyThrows String get(String key) {
        if (PROPS != null) {
            Object maybe = PROPS.get(key);
            return maybe != null ? maybe.toString() : EMPTY;
        }
        PROPS = new Properties();
        PROPS.load(PropsProvider.class.getResourceAsStream("/application.properties"));
        return get(key);
    }
}