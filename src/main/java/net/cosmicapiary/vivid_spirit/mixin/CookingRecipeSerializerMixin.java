package net.cosmicapiary.vivid_spirit.mixin;

import com.google.gson.JsonObject;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.CookingRecipeSerializer;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CookingRecipeSerializer.class)
abstract class CookingRecipeSerializerMixin<T extends AbstractCookingRecipe> implements RecipeSerializer<T> {

	@Inject(method = "read(Lnet/minecraft/util/Identifier;Lcom/google/gson/JsonObject;)Lnet/minecraft/recipe/AbstractCookingRecipe;", at = @At("RETURN"))
	private void addCountToOutput(Identifier identifier, JsonObject jsonObject, CallbackInfoReturnable<T> cir, @Local ItemStack itemStack) {
		int count = JsonHelper.getInt(jsonObject, "resultcount", 1);
		itemStack.setCount(count);
	}
}
