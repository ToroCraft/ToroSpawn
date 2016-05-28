package net.torocraft.torospawnmod.crafting;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.torocraft.torospawnmod.armor.ItemSpawnScanner;

public class ToroSpawnModRecipes {
	
	public static final void init() {
		spawnScanner();
	}
	
	private static void spawnScanner() {
		ItemStack iron = new ItemStack(Items.IRON_INGOT);
		ItemStack emerald = new ItemStack(Items.EMERALD);
		
		GameRegistry.addRecipe(new ItemStack(ItemSpawnScanner.helmetItem), "iii", "iei", 'i', iron, 'e', emerald);
	}	
}
