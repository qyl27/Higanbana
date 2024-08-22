package cx.rain.mc.higanbana.entity;

import cx.rain.mc.higanbana.Higanbana;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Higanbana.MOD_ID, Registries.ENTITY_TYPE);

    public static final RegistrySupplier<EntityType<PlayerSpriteEntity>> SPRITE = ENTITIES.register("sprite", () ->
            EntityType.Builder.of(PlayerSpriteEntity::new, MobCategory.AMBIENT)
//                    .noSave()
//                    .noSummon()
                    .sized(0.6f, 1.8f)
                    .eyeHeight(1.62f)
//                    .vehicleAttachment(Player.DEFAULT_VEHICLE_ATTACHMENT)
                    .clientTrackingRange(64)
                    .updateInterval(2)
                    .build("sprite"));

    public static void register() {
        ENTITIES.register();

    }
}
