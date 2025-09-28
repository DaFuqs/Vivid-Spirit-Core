package net.cosmicapiary.vivid_spirit.custom;

import net.cosmicapiary.vivid_spirit.VividSpirit;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;

public abstract class SpiritFluid extends AbstractFluid {
    @Override
    public Fluid getStill() {
        return VividSpirit.STILL_SPIRIT;
    }

    @Override
    public Fluid getFlowing() {
        return VividSpirit.FLOWING_SPIRIT;
    }

    @Override
    public Item getBucketItem() {
        return VividSpirit.SPIRIT_BUCKET;
    }

    @Override
    protected BlockState toBlockState(FluidState fluidState) {
        return VividSpirit.SPIRIT.getDefaultState().with(Properties.LEVEL_15, getBlockStateLevel(fluidState));
    }

    public static class Flowing extends SpiritFluid {
        @Override
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getLevel(FluidState fluidState) {
            return fluidState.get(LEVEL);
        }

        @Override
        public boolean isStill(FluidState fluidState) {
            return false;
        }
    }

    public static class Still extends SpiritFluid {

        @Override
        public int getLevel(FluidState fluidState) {
            return 8;
        }


        @Override
        public boolean isStill(FluidState fluidState) {
            return true;
        }
    }
}