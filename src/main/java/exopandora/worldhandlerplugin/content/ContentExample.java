package exopandora.worldhandlerplugin.content;

import exopandora.worldhandler.gui.category.Categories;
import exopandora.worldhandler.gui.category.Category;
import exopandora.worldhandler.gui.container.Container;
import exopandora.worldhandler.gui.content.Content;
import exopandora.worldhandler.gui.widget.button.GuiButtonBase;
import exopandora.worldhandler.util.ActionHelper;
import exopandora.worldhandlerplugin.WorldHandlerPlugin;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class ContentExample extends Content
{
	@Override
	public void initButtons(Container container, int x, int y)
	{
		// Register default back and back to game buttons
		
		container.addRenderableWidget(new GuiButtonBase(x, y + 96, 114, 20, Component.translatable("gui.worldhandler.generic.back"), () -> ActionHelper.back(this)));
		container.addRenderableWidget(new GuiButtonBase(x + 118, y + 96, 114, 20, Component.translatable("gui.worldhandler.generic.backToGame"), ActionHelper::backToGame));
		
		// Register custom buttons
		
		container.addRenderableWidget(new GuiButtonBase(x, y, 232, 20, Component.literal("Example button"), () ->
		{
			Minecraft.getInstance().player.connection.sendChat("Example chat messsage");
		}));
	}
	
	@Override
	public Category getCategory()
	{
		return Categories.MAIN;
	}
	
	@Override
	public MutableComponent getTitle()
	{
		return Component.literal("Example Title");
	}
	
	@Override
	public MutableComponent getTabTitle()
	{
		return Component.literal("Example");
	}
	
	@Override
	public Content getActiveContent()
	{
		return Content.REGISTRY.getValue(WorldHandlerPlugin.CONTENT_EXAMPLE_RESOURCE_LOCATION);
	}
}
