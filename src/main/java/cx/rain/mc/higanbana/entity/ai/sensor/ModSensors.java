package cx.rain.mc.higanbana.entity.ai.sensor;

import cx.rain.mc.higanbana.Higanbana;
import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.sensing.SensorType;

public class ModSensors {
    public static final DeferredRegister<SensorType<?>> SENSORS = DeferredRegister.create(Higanbana.MOD_ID, Registries.SENSOR_TYPE);
}
