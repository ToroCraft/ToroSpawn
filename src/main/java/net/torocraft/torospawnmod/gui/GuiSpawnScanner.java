package net.torocraft.torospawnmod.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GuiSpawnScanner extends Gui {

	private Minecraft mc;
	
	public GuiSpawnScanner(Minecraft mc) {
		super();
		this.mc = mc;
	}
	
	@SubscribeEvent
	public void addSpawnScannerGui(RenderGameOverlayEvent event) {
		/*
	     We draw after the ExperienceBar has drawn.  The event raised by GuiIngameForge.pre()
	     will return true from isCancelable.  If you call event.setCanceled(true) in
	     that case, the portion of rendering which this event represents will be canceled.
	     We want to draw *after* the experience bar is drawn, so we make sure isCancelable() returns
	     false and that the eventType represents the ExperienceBar event.
	     */
	    if(event.isCancelable() || event.getType() != ElementType.EXPERIENCE) {      
	    	return;
	    }
	}
	
}
