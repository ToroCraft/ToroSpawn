package net.torocraft.torospawnmod.material;

import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.torocraft.torospawnmod.ToroSpawnMod;

public class ArmorMaterials {
	
	private static final String MODID = ToroSpawnMod.MODID;
	
	public static ArmorMaterial SCANNER = EnumHelper.addArmorMaterial("SCANNER", MODID + ":scanner", 36, new int[]{0, 0, 0, 0}, 10, null);
	
}

