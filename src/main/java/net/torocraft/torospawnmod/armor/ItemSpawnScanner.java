package net.torocraft.torospawnmod.armor;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockSlab.EnumBlockHalf;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
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
			List<String> mobsToSpawn = determineMobsAbleToSpawn(world, player);
			storeSpawnableMobsOnPlayer(player, mobsToSpawn);
		}
	}

	private void storeSpawnableMobsOnPlayer(EntityPlayer player, List<String> mobsToSpawn) {
		NBTTagList list = new NBTTagList();
		for (String mob : mobsToSpawn) {
			list.appendTag(new NBTTagString(mob));
		}
		player.getEntityData().setTag("spawn", list);
	}

	private List<String> determineMobsAbleToSpawn(World world, EntityPlayer player) {
		List<String> mobsToSpawn = new ArrayList<String>();
		if (world.getDifficulty().equals(EnumDifficulty.PEACEFUL)) {
			return mobsToSpawn;
		}
		
		BlockPos playerPos = player.getPosition();
		BlockPos blockPos = playerPos.down(1);
		
		BiomeGenBase biome = world.getBiomeGenForCoords(blockPos);
		if (isBiomeOfType(biome, Type.NETHER)) {
			return netherEntities();
		}
		
		if (isBiomeOfType(biome, Type.END)) {
			return endEntities();
		}
		
		IBlockState blockState = world.getBlockState(blockPos);
		if (isInAir(world, blockPos, blockState)) {
			return mobsToSpawn;
		}
		
		mobsToSpawn.addAll(checkStandardMobConditions(world, player, blockState, blockPos));		
		
		return mobsToSpawn;
	}

	private List<String> endEntities() {
		List<String> endMobs = new ArrayList<String>();
		endMobs.add("enderman");
		if (isInEndCity()) {
			endMobs.add("shulker");
		}
		return endMobs;
	}

	private boolean isInEndCity() {
		// TODO Auto-generated method stub
		return false;
	}

	private List<String> netherEntities() {
		List<String> netherMobs = new ArrayList<String>();
		netherMobs.add("zombiePigman");
		netherMobs.add("ghast");
		if (isInFortress()) {
			netherMobs.add("blaze");
			netherMobs.add("skeleton");
			netherMobs.add("magmaCube");
		}
		return netherMobs;
	}

	private boolean isInFortress() {
		// TODO Auto-generated method stub
		return false;
	}

	private List<String> checkStandardMobConditions(World world, EntityPlayer player, IBlockState blockState, BlockPos blockPos) {		
		List<String> mobList = new ArrayList<String>();
		Block block = blockState.getBlock();
		BlockPos playerPos = player.getPosition();
		int light = world.getChunkFromBlockCoords(playerPos).getLightFor(EnumSkyBlock.BLOCK, playerPos);
		if (light > 7) {
			return mobList;
		}
		
		if (block.getLightOpacity(blockState) == 0) {
			return mobList;
		}
		
		if (block.isLeaves(blockState, world, blockPos)) {
			return mobList;
		}
		
		if (block instanceof BlockSlab) {
			if (!block.isFullBlock(blockState) && blockState.getValue(BlockSlab.HALF) == EnumBlockHalf.BOTTOM) {
				return mobList;
			}
		}
		
		IBlockState playerBlockState = world.getBlockState(playerPos);
		
		if (block.isLadder(playerBlockState, world, playerPos, null)) {
			return mobList;
		}
		
		Block playerBlock = playerBlockState.getBlock();
		if (playerBlock.equals(Blocks.water) || playerBlock.equals(Blocks.flowing_water) || playerBlock.equals(Blocks.lava) || playerBlock.equals(Blocks.flowing_lava)) {
			return mobList;
		}
		
		mobList.add("creeper");
		mobList.add("skeleton");
		mobList.add("zombie");
		mobList.add("spider");
		return mobList;
	}

	private boolean isBiomeOfType(BiomeGenBase biome, Type type) {
		return BiomeDictionary.isBiomeOfType(biome, type);
	}
	
	private boolean isInAir(World world, BlockPos pos, IBlockState state) {
		return state.getBlock().isAir(state, world, pos);
	}

	private boolean isWearingSpawnScanner(EntityPlayer player) {
		for (ItemStack equippedItem: player.getEquipmentAndArmor()) {
			if (equippedItem == null || equippedItem.getItem() == null) {
				continue;
			}
			if (equippedItem.getItem() instanceof ItemSpawnScanner) {
				return true;
			}
		}
		return false;
	}
	
}
