package net.torocraft.torospawnmod.capabilities;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;

public class SpawnCapabilityProvider implements ICapabilityProvider {

	ISpawnCapability instance = new SpawnCapabilityImpl();
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (CapabilitySpawnHandler.SPAWN_CAPABILITY != null && capability == CapabilitySpawnHandler.SPAWN_CAPABILITY) {
			return CapabilitySpawnHandler.SPAWN_CAPABILITY.cast(instance);
		}

		return null;
	}

}
