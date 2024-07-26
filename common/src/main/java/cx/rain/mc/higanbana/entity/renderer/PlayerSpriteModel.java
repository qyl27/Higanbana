package cx.rain.mc.higanbana.entity.renderer;

import cx.rain.mc.higanbana.entity.PlayerSpriteEntity;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;

public class PlayerSpriteModel extends PlayerModel<PlayerSpriteEntity> {
    public PlayerSpriteModel(ModelPart root, boolean slim) {
        super(root, slim);
    }
}
