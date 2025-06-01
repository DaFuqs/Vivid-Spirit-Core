package net.cosmicapiary.vivid_spirit;

import com.google.common.collect.Multimap;
import de.dafuqs.spectrum.SpectrumCommon;
import de.dafuqs.spectrum.api.energy.color.InkColors;
import de.dafuqs.spectrum.api.energy.storage.FixedSingleInkStorage;
import de.dafuqs.spectrum.items.trinkets.InkDrainTrinketItem;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class LifeStealRingItem extends InkDrainTrinketItem {

    public LifeStealRingItem(Settings settings) {
        super(settings, SpectrumCommon.locate("unlocks/trinkets/heartsingers_reward"), InkColors.GRAY);

    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.vivid_spirit.lifestealring.tooltip").formatted(Formatting.GRAY));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        Multimap<EntityAttribute, EntityAttributeModifier> modifiers = super.getModifiers(stack, slot, entity, uuid);

        FixedSingleInkStorage inkStorage = getEnergyStorage(stack);
        long storedInk = inkStorage.getEnergy(inkStorage.getStoredColor());
        int extraHearts = getExtraHearts(storedInk);
        if (extraHearts != 0) {
            modifiers.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(uuid, "vivid_spirit:lifesteal_ring_chance", extraHearts, EntityAttributeModifier.Operation.ADDITION));
            modifiers.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(uuid, "vivid_spirit:lifesteal_ring_amount", extraHearts, EntityAttributeModifier.Operation.ADDITION));
        }
        return modifiers;
    }

    public int getExtraHearts(long storedInk) {
        if (storedInk < 100) {
            return 0;
        } else {
            return 2 + 2 * (int) (Math.log(storedInk / 100.0f) / Math.log(8));
        }
    }
}
