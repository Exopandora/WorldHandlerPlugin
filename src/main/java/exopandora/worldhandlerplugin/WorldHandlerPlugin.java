package exopandora.worldhandlerplugin;

import exopandora.worldhandler.gui.category.Categories;
import exopandora.worldhandler.gui.category.Category;
import exopandora.worldhandler.gui.content.Content;
import exopandora.worldhandler.util.RegistryHelper;
import exopandora.worldhandlerplugin.content.ContentExample;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fmllegacy.network.FMLNetworkConstants;

@Mod(WorldHandlerPlugin.MODID)
public class WorldHandlerPlugin
{
	public static final String MODID = "worldhandlerplugin";
	public static final Content EXAMPLE = new ContentExample();
	
	public WorldHandlerPlugin()
	{
		// Register world handler specific events
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(this::clientSetup);
		modEventBus.addGenericListener(Content.class, this::registerContent);
		modEventBus.addGenericListener(Category.class, this::registerCategory);
		ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(() -> FMLNetworkConstants.IGNORESERVERONLY, (remote, isServer) -> true));
	}
	
	@SubscribeEvent
	public void registerContent(Register<Content> event)
	{
		// Register custom content
		RegistryHelper.register(event.getRegistry(), WorldHandlerPlugin.MODID, "example", WorldHandlerPlugin.EXAMPLE);
	}
	
	@SubscribeEvent
	public void registerCategory(Register<Category> event)
	{
		// Register custom categories here
	}
	
	@SubscribeEvent
	public void clientSetup(FMLClientSetupEvent event)
	{
		// Replace container tab with the example tab
		Categories.MAIN.getContents().set(1, new ResourceLocation(WorldHandlerPlugin.MODID, "example"));
	}
}
