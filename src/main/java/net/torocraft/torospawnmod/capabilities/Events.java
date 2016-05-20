package net.torocraft.torospawnmod.capabilities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Events {

	/*
	 * SubscribeEvent public void harvestDrops(HarvestDropsEvent event) {
	 * IDailiesCapability dailes = getCapability(event.getHarvester()); if
	 * (dailes == null) { return; }
	 * 
	 * dailes.gather(1); System.out.println(dailes.statusMessage());
	 * event.getHarvester().addChatMessage(new
	 * TextComponentString(TextFormatting.RED + "" + dailes.statusMessage())); }
	 *

	// @SideOnly(Side.SERVER)
	@SubscribeEvent
	public void onWorldLoad(WorldEvent.Load event) {

		if (event.getWorld().isRemote) {
			return;
		}

		DailiesRequester requester = new DailiesRequester();
		Set<DailyQuest> dailies = requester.getDailies();

		if (dailies == null) {
			System.out.println("******************* No dailies found, lame!");
		} else {
			System.out.println("********************** Dailies found COUNT[" + dailies.size() + "]");
		}

		if (dailies != null) {
			DailiesWorldData worldData = DailiesWorldData.get(event.getWorld());
			worldData.setDailyQuests(dailies);
		}

	}

	@SubscribeEvent
	public void onSave(PlayerEvent.SaveToFile event) {
		IDailiesCapability dailes = getCapability(event.getEntityPlayer());
		if (dailes == null) {
			return;
		}
		event.getEntityPlayer().getEntityData().setTag(CapabilityDailiesHandler.NAME, dailes.writeNBT());
	}

	@SubscribeEvent
	public void onLoad(PlayerEvent.LoadFromFile event) {
		IDailiesCapability dailes = getCapability(event.getEntityPlayer());
		if (dailes == null) {
			return;
		}
		dailes.readNBT((NBTTagCompound) event.getEntityPlayer().getEntityData().getTag(CapabilityDailiesHandler.NAME));
	}

*/
	private ISpawnCapability getCapability(EntityPlayer player) {
		if (isMissingCapability(player)) {
			return null;
		}
		return player.getCapability(CapabilitySpawnHandler.SPAWN_CAPABILITY, null);
	}

	private boolean isMissingCapability(EntityPlayer player) {
		return player == null || !player.hasCapability(CapabilitySpawnHandler.SPAWN_CAPABILITY, null);
	}

	@SubscribeEvent
	public void onEntityLoad(AttachCapabilitiesEvent.Entity event) {

		if (!(event.getEntity() instanceof EntityPlayer)) {
			return;
		}

		try {
			System.out.println("loading cap to player: " + ((EntityPlayer) event.getEntity()).getName());
		} catch (Exception e) {
			System.out.println("loading cap to player [" + event.getEntity().getClass().getName() + "]");
		}

		event.addCapability(new ResourceLocation(CapabilitySpawnHandler.NAME), new SpawnCapabilityProvider());

	}

}
