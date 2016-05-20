package net.torocraft.torospawnmod;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldSavedData;

public class SpawnWorldData extends WorldSavedData {

	public static final String MODNAME = "ToroSpawnMod";
	
	private Boolean canSpawn;
	
	public SpawnWorldData() {
		super(MODNAME);
	}
	
	public SpawnWorldData(String name) {
		super(name);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		canSpawn = ((NBTTagCompound)nbt.getTag("spawn")).getBoolean("canSpawn");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		NBTTagCompound c = new NBTTagCompound();
		c.setBoolean("canSpawn", canSpawn);
		nbt.setTag("spawn", c);
	}

}
