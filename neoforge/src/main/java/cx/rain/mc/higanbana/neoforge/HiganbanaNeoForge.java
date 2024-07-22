package cx.rain.mc.higanbana.neoforge;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

import cx.rain.mc.higanbana.Higanbana;

@Mod(Higanbana.MOD_ID)
public final class HiganbanaNeoForge {
    public HiganbanaNeoForge(IEventBus bus, ModContainer modContainer) {
        new Higanbana().init();
    }
}
