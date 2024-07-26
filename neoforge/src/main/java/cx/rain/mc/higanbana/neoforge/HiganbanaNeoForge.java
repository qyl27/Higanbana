package cx.rain.mc.higanbana.neoforge;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

import cx.rain.mc.higanbana.Higanbana;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(Higanbana.MOD_ID)
public final class HiganbanaNeoForge {
    private final Higanbana mod = new Higanbana();

    public HiganbanaNeoForge(IEventBus bus, ModContainer modContainer) {
        bus.addListener(this::setup);
        bus.addListener(this::setupClient);
    }

    private void setup(FMLCommonSetupEvent event) {
        mod.init();
    }

    private void setupClient(FMLClientSetupEvent event) {
        mod.initClient();
    }
}
