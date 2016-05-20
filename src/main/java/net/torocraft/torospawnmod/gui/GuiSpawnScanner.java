package net.torocraft.torospawnmod.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GuiSpawnScanner extends Gui {

	private Minecraft mc;
	
	public GuiSpawnScanner(Minecraft mc) {
		super();
		this.mc = mc;
	}
	
	private static final int BUFF_ICON_SIZE = 18;
	private static final int BUFF_ICON_SPACING = BUFF_ICON_SIZE + 2; // 2 pixels between buff icons
	private static final int BUFF_ICON_BASE_U_OFFSET = 0;
	private static final int BUFF_ICON_BASE_V_OFFSET = 198;
	private static final int BUFF_ICONS_PER_ROW = 8;
	
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
	    
	    Entity rve = mc.getRenderViewEntity();
	    
	    if (!(rve instanceof EntityPlayer)) {
	    	return;
	    }
	    
	    EntityPlayer player = (EntityPlayer)rve;
	    
	    NBTTagList tag = (NBTTagList)player.getEntityData().getTag("spawn");
	    
	    int xPos = 2;
	    int yPos = 2;
	    
	    if (tag != null && tag.tagCount() > 0) {
	    	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	        GL11.glDisable(GL11.GL_LIGHTING);      
	        this.mc.renderEngine.bindTexture(new ResourceLocation("/gui/inventory.png"));
	        
	        this.drawTexturedModalRect(
	                xPos, yPos, 
	                BUFF_ICON_BASE_U_OFFSET % BUFF_ICONS_PER_ROW * BUFF_ICON_SIZE, BUFF_ICON_BASE_V_OFFSET / BUFF_ICONS_PER_ROW * BUFF_ICON_SIZE,
	                BUFF_ICON_SIZE, BUFF_ICON_SIZE);
	    }
	    
	}
	
}
