package net.cosmicapiary.vivid_spirit.custom;

import de.dafuqs.spectrum.api.item.Preenchanted;
import de.dafuqs.spectrum.registries.SpectrumEnchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import vectorwing.farmersdelight.common.item.KnifeItem;

import java.util.Map;

public class BedrockKnifeItem extends KnifeItem implements Preenchanted {

    public BedrockKnifeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean isDamageable() { return false; }

    @Override
    public Map<Enchantment, Integer> getDefaultEnchantments() {return Map.of(SpectrumEnchantments.FIRST_STRIKE, 2);}

    @Override
    public ItemStack getDefaultStack() {
        return getDefaultEnchantedStack(this);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }
}