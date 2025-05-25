package net.cosmicapiary.vivid_spirit;

import de.dafuqs.spectrum.blocks.geology.ShimmerstoneOreBlock;
import de.dafuqs.spectrum.registries.SpectrumAdvancements;
import de.dafuqs.spectrum.registries.SpectrumBlocks;
import de.dafuqs.spectrum.registries.SpectrumStatusEffects;
import net.cosmicapiary.vivid_spirit.world.gen.ModWorldGen;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.id.paradiselost.blocks.ParadiseLostBlocks;
import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VividSpirit implements ModInitializer {
	public static final String MOD_ID = "vivid_spirit";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static FlowableFluid STILL_SPIRIT;
	public static FlowableFluid FLOWING_SPIRIT;
	public static Item SPIRIT_BUCKET;
	public static Block SPIRIT;
	@Override
	public void onInitialize() {
		ModWorldGen.addFeatures();
		ModItemGroups.registerItemGroups();

		LOGGER.info("Self-replicating blue goo initialization process...");

		STILL_SPIRIT = Registry.register(Registries.FLUID, new Identifier(MOD_ID, "spirit"),
			new SpiritFluid.Still());

		FLOWING_SPIRIT = Registry.register(Registries.FLUID, new Identifier(MOD_ID, "flowing_spirit"),
			new SpiritFluid.Flowing());

		SPIRIT_BUCKET = Registry.register(Registries.ITEM, new Identifier(MOD_ID, "spirit_bucket"),
			new BucketItem(STILL_SPIRIT, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));

		SPIRIT = Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "spirit"),
			new FluidBlock(STILL_SPIRIT, FabricBlockSettings.copyOf(Blocks.WATER).luminance(15).mapColor(MapColor.BRIGHT_TEAL)));
	}
	public static final Block BLACK_ICE = registerBlock("black_ice",
			new Block(FabricBlockSettings.copyOf(Blocks.BLUE_ICE)
					.mapColor(MapColor.BLACK)
					.nonOpaque()
					.slipperiness(1.05f)
			));
	public static final Item RAW_SILVER_NUGGET = registerItem("raw_silver_nugget",
			new Item(new FabricItemSettings()));
	public static final Item PURE_RUBY = registerItem("pure_ruby",
			new Item(new FabricItemSettings()));
	public static final Item PURE_SILVER = registerItem("pure_silver",
			new Item(new FabricItemSettings()));

	public static final Item LIQUID_PEARLS = registerItem("liquid_pearls",
			new Item(liquidPearlSettings()));


	public static final Block BLACKSLAG_ZINC_ORE = registerBlock("blackslag_zinc_ore",
			new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_IRON_ORE)
			));
	public static final Block BLACKSLAG_SILVER_ORE = registerBlock("blackslag_silver_ore",
			new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_IRON_ORE)
			));
	public static final Block STARRY_CLUMP = registerBlock("starry_clump",
			new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_IRON_ORE)
			));
	public static final Block SHIMMERSTONE_ORE = registerBlock("shimmerstone_ore",
			new ShimmerstoneOreBlock(FabricBlockSettings.copyOf(SpectrumBlocks.SHIMMERSTONE_ORE).ticksRandomly(),
					UniformIntProvider.create(2, 4), SpectrumAdvancements.REVEAL_SHIMMERSTONE, ParadiseLostBlocks.FLOESTONE.getDefaultState()
			));
	public static final Block FLOESTONE_REDSTONE_ORE = registerBlock("floestone_redstone_ore",
			new RedstoneOreBlock(FabricBlockSettings.copyOf(Blocks.REDSTONE_ORE)
			));
	public static final Block PURE_RUBY_BLOCK = registerBlock("pure_ruby_block",
			new Block(FabricBlockSettings.copyOf(Blocks.REDSTONE_BLOCK)
			));
	public static final Block SMALL_RUBY_BUD = registerBlock("small_ruby_bud",
			new AmethystClusterBlock(3, 4, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)
			));
	public static final Block LARGE_RUBY_BUD = registerBlock("large_ruby_bud",
			new AmethystClusterBlock(5, 3, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)
			));
	public static final Block RUBY_CLUSTER = registerBlock("ruby_cluster",
			new AmethystClusterBlock(7, 3, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)
			));

	//public static final Block DECAYED_ONYX = registerBlock("decayed_onyx", new Block(FabricBlockSettings.copyOf(SpectrumBlocks.ONYX_BLOCK)		));

	public static final Block PURE_SILVER_BLOCK = registerBlock("pure_silver_block",
			new Block(FabricBlockSettings.copyOf(Blocks.REDSTONE_BLOCK)
			));
	public static final Block SMALL_SILVER_BUD = registerBlock("small_silver_bud",
			new AmethystClusterBlock(3, 4, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)
			));
	public static final Block LARGE_SILVER_BUD = registerBlock("large_silver_bud",
			new AmethystClusterBlock(5, 3, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)
			));
	public static final Block SILVER_CLUSTER = registerBlock("silver_cluster",
			new AmethystClusterBlock(7, 3, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)
			));

	private static Block registerBlock(String name, Block block){
		registerBlockItem(name, block);
		return Registry.register(Registries.BLOCK, new Identifier(VividSpirit.MOD_ID, name), block);
	}
	private static void registerBlockItem(String name, Block block){
		Registry.register(Registries.ITEM, new Identifier(VividSpirit.MOD_ID, name),
				new BlockItem(block, new FabricItemSettings()));
	}
	private static Item registerItem(String name, Item item) {
		return Registry.register(Registries.ITEM, new Identifier(VividSpirit.MOD_ID, name), item);
	}

	private static FabricItemSettings liquidPearlSettings() {
		return new FabricItemSettings().food(new FoodComponent.Builder().hunger(2).saturationModifier((float) 0.0)
				.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 300, 0), 0.5F)
				.statusEffect(new StatusEffectInstance(SpectrumStatusEffects.SOMNOLENCE, 300, 0), 0.5F)
				.statusEffect(new StatusEffectInstance(SpectrumStatusEffects.NOURISHING, 40, 0), 0.5F)
				.statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 1, 1), 0.5F)
				.alwaysEdible()
				.snack().build());
	}
}
