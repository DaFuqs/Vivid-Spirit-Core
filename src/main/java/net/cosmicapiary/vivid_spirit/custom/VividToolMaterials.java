package net.cosmicapiary.vivid_spirit.custom;

import com.google.common.base.Suppliers;
import de.dafuqs.spectrum.registries.SpectrumItems;
import net.minecraft.recipe.Ingredient;

import java.util.function.Supplier;

import static net.minecraft.item.ToolMaterials.IRON;

public class VividToolMaterials {

    public enum ToolMaterial implements net.minecraft.item.ToolMaterial {
        FAKE_CRYSTAL(IRON.getMiningLevel(), 16, 4.0F, 2.0F, 10, Ingredient::empty),
        FAKE_BEDROCK(4, 0, 15.0F, 5.0F, 3, () -> Ingredient.ofItems(SpectrumItems.BEDROCK_DUST));

        private final int miningLevel;
        private final int itemDurability;
        private final float miningSpeed;
        private final float attackDamage;
        private final int enchantability;
        private final Supplier<Ingredient> repairIngredient;

        ToolMaterial(int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
            this.miningLevel = miningLevel;
            this.itemDurability = itemDurability;
            this.miningSpeed = miningSpeed;
            this.attackDamage = attackDamage;
            this.enchantability = enchantability;
            this.repairIngredient = Suppliers.memoize(repairIngredient::get);
        }

        @Override
        public int getDurability() {
            return this.itemDurability;
        }

        @Override
        public float getMiningSpeedMultiplier() {
            return this.miningSpeed;
        }

        @Override
        public float getAttackDamage() {
            return this.attackDamage;
        }

        @Override
        public int getMiningLevel() {
            return this.miningLevel;
        }

        @Override
        public int getEnchantability() {
            return this.enchantability;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return this.repairIngredient.get();
        }
    }

}