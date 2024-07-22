package cx.rain.mc.higanbana.fabric;

import net.fabricmc.api.ModInitializer;

import cx.rain.mc.higanbana.Higanbana;

public final class HiganbanaFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        new Higanbana().init();
    }
}
