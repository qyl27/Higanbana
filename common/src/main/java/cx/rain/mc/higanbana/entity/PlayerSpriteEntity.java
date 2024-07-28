package cx.rain.mc.higanbana.entity;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.yggdrasil.ProfileResult;
import net.minecraft.Util;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.server.Services;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class PlayerSpriteEntity extends LivingEntity {
    private final UUID fangLuo = UUID.fromString("7469d94d-cbf1-441a-b5a8-ed54794129ad");

    @Nullable
    private PlayerSkin skin;

    public PlayerSpriteEntity(EntityType<PlayerSpriteEntity> type, Level level) {
        super(type, level);
        Objects.requireNonNull(getAttribute(Attributes.MAX_HEALTH)).setBaseValue(20);
    }

    @Override
    public @NotNull Iterable<ItemStack> getArmorSlots() {
        return List.of();
    }

    @Override
    public @NotNull ItemStack getItemBySlot(EquipmentSlot slot) {
        return ItemStack.EMPTY;
    }

    @Override
    public void setItemSlot(EquipmentSlot slot, ItemStack stack) {

    }

    @Override
    public @NotNull HumanoidArm getMainArm() {
        return HumanoidArm.RIGHT;
    }

    @Override
    public @NotNull AttributeMap getAttributes() {
        return new AttributeMap(LivingEntity.createLivingAttributes().build());
    }

    public PlayerSkin getSkin() {
        if (skin == null) {
            // Todo: read from nbt.
        }

        return skin;
    }

    public float getDisappearPercentage(Player player) {
        return 0.7f;
    }

    public static CompletableFuture<Optional<GameProfile>> fetchProfile(UUID id, Services services) {
        return CompletableFuture.supplyAsync(() -> {
            ProfileResult profileResult = services.sessionService().fetchProfile(id, false);
            return Optional.ofNullable(profileResult).map(ProfileResult::profile);
        }, Util.backgroundExecutor());
    }

    /// <editor-fold desc="Soul.">

    @Override
    protected boolean isAffectedByFluids() {
        return false;
    }

    @Override
    protected boolean shouldDropLoot() {
        return false;
    }

    @Override
    public boolean shouldDropExperience() {
        return false;
    }

    @Override
    public boolean isSpectator() {
        return true;
    }

    @Override
    public boolean canBeSeenAsEnemy() {
        return false;
    }

    @Override
    public boolean canBeSeenByAnyone() {
        return isAlive();
    }

    @Override
    public boolean canBeAffected(MobEffectInstance effectInstance) {
        return false;
    }

    @Override
    public boolean isPickable() {
        return false;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        // Todo.
        return super.isInvulnerableTo(source);
    }

    /// </editor-fold>
}
