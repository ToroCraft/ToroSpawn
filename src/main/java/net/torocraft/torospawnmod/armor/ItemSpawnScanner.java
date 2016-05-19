package net.torocraft.torospawnmod.armor;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
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
	
}
