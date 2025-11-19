package net.cosmicapiary.vivid_spirit.mixin;

import de.dafuqs.spectrum.blocks.titration_barrel.TitrationBarrelBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TitrationBarrelBlock.class)
abstract class TitrationBarrelBlockMixin {

    @Redirect(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isIn(Lnet/minecraft/registry/tag/TagKey;)Z"))
    public boolean sealWithAnyPlank(ItemStack instance, TagKey<Item> tag) {
        return instance.isIn(ItemTags.PLANKS);
    }
}
