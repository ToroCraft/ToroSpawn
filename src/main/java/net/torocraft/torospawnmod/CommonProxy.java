package net.torocraft.torospawnmod;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.torocraft.torospawnmod.crafting.ToroSpawnModRecipes;
import net.torocraft.torospawnmod.item.ToroSpawnModItems;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent e) {

    }

    public void init(FMLInitializationEvent e) {
        ToroSpawnModItems.init();
    	ToroSpawnModRecipes.init();
    }

    public void postInit(FMLPostInitializationEvent e) {

    }
}
