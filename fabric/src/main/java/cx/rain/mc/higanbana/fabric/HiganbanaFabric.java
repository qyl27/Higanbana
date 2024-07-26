package cx.rain.mc.higanbana.fabric;

import net.fabricmc.api.ModInitializer;

import cx.rain.mc.higanbana.Higanbana;

public final class HiganbanaFabric implements ModInitializer {
    public static final Higanbana MOD = new Higanbana();

    @Override
    public void onInitialize() {
        MOD.init();
    }
}
