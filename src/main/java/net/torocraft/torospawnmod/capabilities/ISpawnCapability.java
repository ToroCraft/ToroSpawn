package net.torocraft.torospawnmod.capabilities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public interface ISpawnCapability {

	boolean canMobSpawn(EntityPlayer player);
	
	NBTTagCompound writeNBT();

	void readNBT(NBTTagCompound c);
	
}
