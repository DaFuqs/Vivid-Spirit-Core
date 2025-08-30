package net.cosmicapiary.vivid_spirit;

import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.client.item.*;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.pathing.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.screen.*;
import net.minecraft.server.network.*;
import net.minecraft.server.world.*;
import net.minecraft.text.*;
import net.minecraft.util.*;
import net.minecraft.util.hit.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraft.world.event.listener.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class PigmentSiphonBlock extends BlockWithEntity {

    public PigmentSiphonBlock(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PigmentSiphonBlockEntity(pos, state);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        super.appendTooltip(stack, world, tooltip, options);
        tooltip.add(Text.translatable("block.vivid_spirit.pigment_siphon.tooltip").formatted(Formatting.GRAY));
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof PigmentSiphonBlockEntity crystalApothecaryBlockEntity) {
            if (placer instanceof ServerPlayerEntity serverPlayerEntity) {
                crystalApothecaryBlockEntity.setOwner(serverPlayerEntity);
            }
            if (itemStack.hasCustomName()) {
                crystalApothecaryBlockEntity.setCustomName(itemStack.getName());
            }
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.SUCCESS;
        } else {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof PigmentSiphonBlockEntity crystalApothecaryBlockEntity) {
                player.openHandledScreen(crystalApothecaryBlockEntity);
            }
            return ActionResult.CONSUME;
        }
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos));
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.isOf(newState.getBlock())) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof PigmentSiphonBlockEntity crystalApothecaryBlockEntity) {
                ItemScatterer.spawn(world, pos, crystalApothecaryBlockEntity);
                world.updateComparators(pos, this);
            }

            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, VividBlockEntities.PIGMENT_SIPHON, PigmentSiphonBlockEntity::tick);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> GameEventListener getGameEventListener(ServerWorld world, T blockEntity) {
        return blockEntity instanceof PigmentSiphonBlockEntity crystalApothecaryBlockEntity ? crystalApothecaryBlockEntity.getEventListener() : null;
    }

}
