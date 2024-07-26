package cx.rain.mc.higanbana.entity;

import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class PlayerSpriteEntity extends LivingEntity {
    // Todo: read from nbt.
    private UUID uuid = UUID.fromString("7469d94d-cbf1-441a-b5a8-ed54794129ad");

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

    public UUID getSkinUuid() {
        return uuid;
    }
}
