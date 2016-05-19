package net.torocraft.torospawnmod.armor;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockSlab.EnumBlockHalf;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.torocraft.torospawnmod.ToroSpawnMod;
import net.torocraft.torospawnmod.material.ArmorMaterials;
import net.torocraft.torospawnmod.util.ToroSpawnUtils;

public class ItemSpawnScanner extends ItemArmor {

	public ItemSpawnScanner(String unlocalizedName, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(ArmorMaterials.SCANNER, renderIndexIn, equipmentSlotIn);
		this.setUnlocalizedName(unlocalizedName);
		setMaxDamage(8588);
	}

	public static final String NAME = "scanner";

	public static ItemSpawnScanner helmetItem;

	public static void init() {
		initHelmet();
	}
	
	private static void initHelmet() {
		helmetItem = new ItemSpawnScanner(NAME + "_helmet", 1, EntityEquipmentSlot.HEAD);
		ToroSpawnUtils.registerItem(helmetItem, NAME + "_helmet");
	}
	
	public static void registerRenders() {
		registerRendersHelmet();
	}
	
	private static void registerRendersHelmet() {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(helmetItem, 0, model("helmet"));
	}
	
	private static ModelResourceLocation model(String model) {
		return new ModelResourceLocation(ToroSpawnMod.MODID + ":" + NAME + "_" + model, "inventory");
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {		
		if (isWearingSpawnScanner(player)) {
			checkIfMobCanSpawn(world, player);
		}
	}

	private void checkIfMobCanSpawn(World world, EntityPlayer player) {
		if (world.getDifficulty().equals(EnumDifficulty.PEACEFUL)) {
			return;
		}
		
		IBlockState blockState = world.getBlockState(player.getPosition());
		if (blockMeetsSpawnConditions(blockState)) {
			player.addChatMessage(new TextComponentString("enemy can spawn here"));
		} else {
			player.addChatMessage(new TextComponentString("enemy canNOT spawn here"));
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
