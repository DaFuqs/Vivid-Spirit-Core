package net.cosmicapiary.vivid_spirit.custom;

import com.sammy.malum.common.item.curiosities.weapons.scythe.MagicScytheItem;
import com.sammy.malum.registry.common.item.EnchantmentRegistry;
import de.dafuqs.spectrum.api.item.Preenchanted;
import net.minecraft.enchantment.*;
import net.minecraft.item.*;
import java.util.*;

public class BedrockScytheItem extends MagicScytheItem implements Preenchanted {

    public BedrockScytheItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, 5, settings);
    }

    @Override
    public boolean isDamageable() { return false; }


    @Override
    public Map<Enchantment, Integer> getDefaultEnchantments() {return Map.of(EnchantmentRegistry.HAUNTED.get(), 2);}

    @Override
    public ItemStack getDefaultStack() {
        return getDefaultEnchantedStack(this);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

}
