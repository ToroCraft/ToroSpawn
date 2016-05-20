package net.torocraft.torospawnmod.capabilities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class SpawnCapabilityImpl implements ISpawnCapability {

	private Boolean canSpawn;
	
	@Override
	public boolean canMobSpawn(EntityPlayer player) {
		if (canSpawn == null) {
			return false;
		}
		return canSpawn;
	}

	@Override
	public NBTTagCompound writeNBT() {
		NBTTagCompound c = new NBTTagCompound();
		c.setBoolean("canSpawn", canSpawn);
		return c;
	}

	@Override
	public void readNBT(NBTTagCompound c) {
		if (c == null) {
			canSpawn = false;
			return;
		}
		
		canSpawn = c.getBoolean("canSpawn");
	}

}
