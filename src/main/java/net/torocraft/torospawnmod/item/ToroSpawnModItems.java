
package net.torocraft.torospawnmod.item;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.torocraft.torospawnmod.armor.ItemSpawnScanner;

public class ToroSpawnModItems {

	public static final void init() {
		initTools();
	}

	private static void initTools() {
		ItemSpawnScanner.init();
	}
	
	@SideOnly(Side.CLIENT)
	public static final void registerRenders() {
		ItemSpawnScanner.registerRenders();
	}

}
