package net.torocraft.torospawnmod.capabilities;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.torocraft.torospawnmod.ToroSpawnMod;

public class CapabilitySpawnHandler {
	
	public static final String NAME = ToroSpawnMod.MODNAME + ":CapabilitySpawn";

	@CapabilityInject(ISpawnCapability.class)
	public static Capability<ISpawnCapability> SPAWN_CAPABILITY = null;

	public static void register() {
		CapabilityManager.INSTANCE.register(ISpawnCapability.class, new SpawnStorage(), SpawnCapabilityImpl.class);
		MinecraftForge.EVENT_BUS.register(new Events());
	}

}
