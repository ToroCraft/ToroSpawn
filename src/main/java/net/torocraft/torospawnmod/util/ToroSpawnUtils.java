package net.torocraft.torospawnmod.util;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.IForgeRegistryEntry;

public class ToroSpawnUtils {
	
	public static final String RESOURCE_DOMAIN = "torospawnmod"; 

	public static <K extends IForgeRegistryEntry<?>> K registerItem(K object, String domain, String name) {
		ResourceLocation resourceName = new ResourceLocation(domain, name);
		return GameRegistry.register(object, resourceName);
	}
	
	public static <K extends IForgeRegistryEntry<?>> K registerItem(K object, String name) {
		return registerItem(object, RESOURCE_DOMAIN, name);
	}
	
}
