package net.torocraft.torospawnmod.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class SpawnStorage implements IStorage<ISpawnCapability> {

	@Override
	public NBTBase writeNBT(Capability<ISpawnCapability> capability, ISpawnCapability instance, EnumFacing side) {
		return instance.writeNBT();
	}

	@Override
	public void readNBT(Capability<ISpawnCapability> capability, ISpawnCapability instance, EnumFacing side, NBTBase base) {
		if (instance == null) {
			return;
		}

		NBTTagCompound c = null;

		if (base != null && base instanceof NBTTagCompound) {
			c = (NBTTagCompound) base;
		}

		instance.readNBT(c);
	}

}
