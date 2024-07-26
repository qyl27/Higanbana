package cx.rain.mc.higanbana.fabric.client;

import cx.rain.mc.higanbana.fabric.HiganbanaFabric;
import net.fabricmc.api.ClientModInitializer;

public final class HiganbanaFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HiganbanaFabric.MOD.initClient();
    }
}
