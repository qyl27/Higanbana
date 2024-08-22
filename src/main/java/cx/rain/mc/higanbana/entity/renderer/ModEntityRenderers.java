package cx.rain.mc.higanbana.entity.renderer;

import cx.rain.mc.higanbana.entity.ModEntities;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;

public class ModEntityRenderers {
    public static void register() {
        EntityRendererRegistry.register(ModEntities.SPRITE, PlayerSpriteRenderer::new);
    }
}
