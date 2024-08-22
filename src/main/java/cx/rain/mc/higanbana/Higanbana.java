package cx.rain.mc.higanbana;

import cx.rain.mc.higanbana.entity.ModEntities;
import cx.rain.mc.higanbana.entity.renderer.ModEntityRenderers;

import java.time.ZonedDateTime;
import java.util.Properties;

public final class Higanbana {
    public static final String MOD_ID = "higanbana";

    public static final String VERSION;
    public static final ZonedDateTime BUILD_TIME;

    static {
        var properties = new Properties();
        String version;
        ZonedDateTime buildTime;
        try {
            properties.load(Higanbana.class.getResourceAsStream("/build_info.properties"));
            version = properties.getProperty("mod_version") + "+mc" + properties.getProperty("minecraft_version");
            buildTime = ZonedDateTime.parse(properties.getProperty("build_time"));
        } catch (Exception ignored) {
            version = "Unknown";
            buildTime = null;
        }
        BUILD_TIME = buildTime;
        VERSION = version;
    }

    public Higanbana() {

    }

    public void init() {
        ModEntities.register();
    }

    public void initClient() {
        ModEntityRenderers.register();
    }
}
