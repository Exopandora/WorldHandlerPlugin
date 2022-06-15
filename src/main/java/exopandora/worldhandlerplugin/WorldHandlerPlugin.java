package exopandora.worldhandlerplugin;

import exopandora.worldhandler.gui.category.Categories;
import exopandora.worldhandler.gui.category.Category;
import exopandora.worldhandler.gui.content.Content;
import exopandora.worldhandlerplugin.content.ContentExample;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.IExtensionPoint.DisplayTest;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;

@Mod(WorldHandlerPlugin.MODID)
public class WorldHandlerPlugin
{
	public static final String MODID = "worldhandlerplugin";
	public static final ResourceLocation CONTENT_EXAMPLE_RESOURCE_LOCATION = new ResourceLocation(WorldHandlerPlugin.MODID, "example");
	
	public WorldHandlerPlugin()
	{
		// Register world handler specific events
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(this::clientSetup);
		modEventBus.addListener(this::registerContent);
		modEventBus.addListener(this::registerCategory);
		ModLoadingContext.get().registerExtensionPoint(DisplayTest.class, () -> new DisplayTest(() -> "ANY", (remote, isServer) -> true));
	}
	
	@SubscribeEvent
	public void registerContent(RegisterEvent event)
	{
		// Register custom content
		if(event.getRegistryKey().equals(Content.REGISTRY_KEY))
		{
			event.register(Content.REGISTRY_KEY, CONTENT_EXAMPLE_RESOURCE_LOCATION, () -> new ContentExample());
		}
	}
	
	@SubscribeEvent
	public void registerCategory(RegisterEvent event)
	{
		if(event.getRegistryKey().equals(Category.REGISTRY_KEY))
		{
			// Register custom categories here
		}
	}
	
	@SubscribeEvent
	public void clientSetup(FMLClientSetupEvent event)
	{
		// Replace container tab with the example tab
		Categories.MAIN.getContents().set(1, new ResourceLocation(WorldHandlerPlugin.MODID, "example"));
	}
}
