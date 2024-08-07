package cx.rain.mc.higanbana.entity.renderer;

import com.mojang.authlib.GameProfile;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import cx.rain.mc.higanbana.entity.PlayerSpriteEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.system.MemoryUtil;

public class PlayerSpriteRenderer extends LivingEntityRenderer<PlayerSpriteEntity, PlayerModel<PlayerSpriteEntity>> {
    private final PlayerModel<PlayerSpriteEntity> normalModel;
    private final PlayerModel<PlayerSpriteEntity> slimModel;

//    private GameProfile gameProfile;

    public PlayerSpriteRenderer(EntityRendererProvider.Context context) {
        super(context, null, 0);
        this.normalModel = new PlayerModel<>(context.bakeLayer(ModelLayers.PLAYER), false);
        this.slimModel = new PlayerModel<>(context.bakeLayer(ModelLayers.PLAYER_SLIM), true);
//        model = this.normalModel;
    }

    @Override
    public void render(PlayerSpriteEntity entity, float entityYaw, float partialTicks,
                       PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        var model = this.normalModel;
        if (entity.getSkin().model() == PlayerSkin.Model.SLIM) {
            model = this.slimModel;
        }

        float k;
        Direction direction;
        Entity entity2;
        poseStack.pushPose();
        model.attackTime = this.getAttackAnim(entity, partialTicks);
        model.riding = entity.isPassenger();
        model.young = entity.isBaby();
        float f = Mth.rotLerp(partialTicks, entity.yBodyRotO, entity.yBodyRot);
        float g = Mth.rotLerp(partialTicks, entity.yHeadRotO, entity.yHeadRot);
        float h = g - f;
        if (entity.isPassenger() && (entity2 = entity.getVehicle()) instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity)entity2;
            f = Mth.rotLerp(partialTicks, livingEntity.yBodyRotO, livingEntity.yBodyRot);
            h = g - f;
            float i = Mth.wrapDegrees(h);
            if (i < -85.0f) {
                i = -85.0f;
            }
            if (i >= 85.0f) {
                i = 85.0f;
            }
            f = g - i;
            if (i * i > 2500.0f) {
                f += i * 0.2f;
            }
            h = g - f;
        }
        float j = Mth.lerp(partialTicks, entity.xRotO, entity.getXRot());
        if (LivingEntityRenderer.isEntityUpsideDown(entity)) {
            j *= -1.0f;
            h *= -1.0f;
        }
        h = Mth.wrapDegrees(h);
        if (entity.hasPose(Pose.SLEEPING) && (direction = entity.getBedOrientation()) != null) {
            k = entity.getEyeHeight(Pose.STANDING) - 0.1f;
            poseStack.translate((float)(-direction.getStepX()) * k, 0.0f, (float)(-direction.getStepZ()) * k);
        }
        float i = entity.getScale();
        poseStack.scale(i, i, i);
        k = this.getBob(entity, partialTicks);
        this.setupRotations(entity, poseStack, k, f, partialTicks, i);
        poseStack.scale(-1.0f, -1.0f, 1.0f);
        this.scale(entity, poseStack, partialTicks);
        poseStack.translate(0.0f, -1.501f, 0.0f);
        float l = 0.0f;
        float m = 0.0f;
        if (!entity.isPassenger() && entity.isAlive()) {
            l = entity.walkAnimation.speed(partialTicks);
            m = entity.walkAnimation.position(partialTicks);
            if (entity.isBaby()) {
                m *= 3.0f;
            }
            if (l > 1.0f) {
                l = 1.0f;
            }
        }
        model.prepareMobModel(entity, m, l, partialTicks);
        model.setupAnim(entity, m, l, k, h, j);
        RenderType renderType = getRenderType(entity, true, false, false);
        if (renderType != null) {
            VertexConsumer vertexConsumer = buffer.getBuffer(renderType);
            BufferBuilder builder = (BufferBuilder) vertexConsumer;
            int n = LivingEntityRenderer.getOverlayCoords(entity, this.getWhiteOverlayProgress(entity, partialTicks));
            model.renderToBuffer(poseStack, builder, packedLight, n, -1);

            var cellSize = builder.vertexSize;
            var pointer = builder.buffer.pointer;

            var maxY = Float.NEGATIVE_INFINITY;
            var minY = Float.POSITIVE_INFINITY;
            for (var offset = 0; offset < builder.buffer.writeOffset; offset += cellSize) {
                var y = MemoryUtil.memGetFloat(pointer + offset + 4L);

                if (y > maxY) {
                    maxY = y;
                }

                if (y < minY) {
                    minY = y;
                }
            }

            var lineY = (maxY - minY) * entity.getDisappearPercentage(Minecraft.getInstance().player) + minY;
            for (var offset = 0; offset < builder.buffer.writeOffset; offset += cellSize) {
                var y = MemoryUtil.memGetFloat(pointer + offset + 4L);

                if (y < lineY) {
                    MemoryUtil.memPutByte(pointer + offset + 15L, (byte) 0x00);
                }
            }
        }
        if (!entity.isSpectator()) {
            for (var layer : this.layers) {
                layer.render(poseStack, buffer, packedLight, entity, m, l, partialTicks, k, h, j);
            }
        }

        poseStack.popPose();
        this.renderNameTag(entity, entity.getDisplayName(), poseStack, buffer, packedLight, partialTicks);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(PlayerSpriteEntity entity) {
        return entity.getSkin().texture();
    }
}
