package net.cosmicapiary.vivid_spirit.custom;

import de.dafuqs.spectrum.api.item.Preenchanted;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

import java.util.Map;

public class CeremonialFalchionItem extends SwordItem implements Preenchanted {

    public CeremonialFalchionItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public Map<Enchantment, Integer> getDefaultEnchantments() {
        return Map.of(Enchantments.UNBREAKING, 3);
    }

    @Override
    public ItemStack getDefaultStack() {
        return getDefaultEnchantedStack(this);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return false;
    }
}