package net.cosmicapiary.vivid_spirit;

import com.google.common.collect.Multimap;
import de.dafuqs.spectrum.SpectrumCommon;
import de.dafuqs.spectrum.api.energy.color.InkColors;
import de.dafuqs.spectrum.api.energy.storage.FixedSingleInkStorage;
import de.dafuqs.spectrum.items.trinkets.InkDrainTrinketItem;
import de.dafuqs.spectrum.items.trinkets.SpectrumTrinketItem;
import de.dafuqs.spectrum.networking.SpectrumS2CPacketSender;
import de.dafuqs.spectrum.registries.SpectrumStatusEffects;
import de.dafuqs.spectrum.status_effects.DivinityStatusEffect;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class RetaliationRingItem extends SpectrumTrinketItem {

    private static final int TRIGGER_EVERY_X_TICKS = 240;
    private static final int EFFECT_DURATION = TRIGGER_EVERY_X_TICKS + 10;


    public RetaliationRingItem(Settings settings) {
        super(settings, SpectrumCommon.locate("unlocks/trinkets/heartsingers_reward"));

    }

    @Override
    public void onEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        super.onEquip(stack, slot, entity);
        giveEffect(entity);
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        super.tick(stack, slot, entity);
        World world = entity.getWorld();
        if (!world.isClient && world.getTime() % TRIGGER_EVERY_X_TICKS == 0) {
            giveEffect(entity);
        }
    }

    private static void giveEffect(LivingEntity entity) {
        entity.addStatusEffect(new StatusEffectInstance(SpectrumStatusEffects.LIGHTWEIGHT, EFFECT_DURATION, 1, true, true));
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, EFFECT_DURATION, 1, true, true));
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, EFFECT_DURATION, 1, true, true));
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.add(Text.translatable("item.vivid_spirit.retaliation_ring.tooltip").formatted(Formatting.GRAY));
    }
}
