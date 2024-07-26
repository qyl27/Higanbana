package cx.rain.mc.higanbana;

import cx.rain.mc.higanbana.entity.ModEntities;
import cx.rain.mc.higanbana.entity.renderer.ModEntityRenderers;

public final class Higanbana {
    public static final String MOD_ID = "higanbana";

    public Higanbana() {

    }

    public void init() {
        ModEntities.register();
    }

    public void initClient() {
        ModEntityRenderers.register();
    }
}
