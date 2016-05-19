package net.torocraft.torospawnmod.events;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockSlab.EnumBlockHalf;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.EnumDifficulty;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.torocraft.torospawnmod.armor.ItemSpawnScanner;

public class EventHooks {

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void checkSpawnConditions(LivingUpdateEvent event) {
		if(!(event.getEntityLiving() instanceof EntityPlayer)) {
			return;
		}
		
		EntityPlayer player = (EntityPlayer)event.getEntityLiving();
		if (isWearingSpawnScanner(player)) {
			checkIfMobCanSpawn(event, player);
		}
	}

	private void checkIfMobCanSpawn(LivingUpdateEvent event, EntityPlayer player) {
		if (player.getEntityWorld().getDifficulty().equals(EnumDifficulty.PEACEFUL)) {
			return;
		}
		
		IBlockState blockState = player.getEntityWorld().getBlockState(player.getPosition());
		if (blockMeetsSpawnConditions(blockState)) {
			
		} else {
			
		}
	}

	private boolean blockMeetsSpawnConditions(IBlockState blockState) {
		Block block = blockState.getBlock();
		int light = block.getLightValue(blockState);
		
		if (light > 7) {
			return false;
		}
		
		if (!block.isFullyOpaque(blockState)) {
			return false;
		}
		
		if (block instanceof BlockSlab) {
			if (!block.isFullBlock(blockState) && blockState.getValue(BlockSlab.HALF).equals(EnumBlockHalf.BOTTOM)) {
				return false;
			}
		}
		
		return true;
	}

	private boolean isWearingSpawnScanner(EntityPlayer player) {
		for (ItemStack equippedItem: player.getEquipmentAndArmor()) {
			if (equippedItem.getItem() instanceof ItemSpawnScanner) {
				return true;
			}
		}
		return false;
	}
	
}
