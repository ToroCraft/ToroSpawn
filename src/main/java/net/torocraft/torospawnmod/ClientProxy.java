package net.torocraft.torospawnmod;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.torocraft.torospawnmod.gui.GuiSpawnScanner;
import net.torocraft.torospawnmod.item.ToroSpawnModItems;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
        ToroSpawnModItems.registerRenders();
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
        MinecraftForge.EVENT_BUS.register(new GuiSpawnScanner(Minecraft.getMinecraft()));
    }

}