package net.torocraft.torospawnmod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod (modid = ToroSpawnMod.MODID, name = ToroSpawnMod.MODNAME, version = ToroSpawnMod.VERSION)
public class ToroSpawnMod {
	
	public static final String MODID = "torospawnmod";
	public static final String VERSION = "1.9.4-1";
	public static final String MODNAME = "ToroSpawnMod";
	
	@SidedProxy(clientSide="net.torocraft.torospawnmod.ClientProxy", serverSide="net.torocraft.torospawnmod.ServerProxy")
	public static CommonProxy proxy;
	
	@Instance(value = ToroSpawnMod.MODID)
	public static ToroSpawnMod instance;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
	    proxy.preInit(e);
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
	    proxy.init(e);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
	    proxy.postInit(e);
	}
	
}
