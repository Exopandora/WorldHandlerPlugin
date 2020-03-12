package exopandora.worldhandlerplugin.content;

import exopandora.worldhandler.gui.button.GuiButtonBase;
import exopandora.worldhandler.gui.category.Categories;
import exopandora.worldhandler.gui.category.Category;
import exopandora.worldhandler.gui.container.Container;
import exopandora.worldhandler.gui.content.Content;
import exopandora.worldhandler.util.ActionHelper;
import exopandora.worldhandlerplugin.WorldHandlerPlugin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ContentExample extends Content
{
	@Override
	public void initButtons(Container container, int x, int y)
	{
		// Register default back and back to game buttons
		
		container.add(new GuiButtonBase(x, y + 96, 114, 20, I18n.format("gui.worldhandler.generic.back"), () -> ActionHelper.back(this)));
		container.add(new GuiButtonBase(x + 118, y + 96, 114, 20, I18n.format("gui.worldhandler.generic.backToGame"), ActionHelper::backToGame));
		
		// Register custom buttons
		
		container.add(new GuiButtonBase(x, y, 232, 20, "Example button", () ->
		{
			Minecraft.getInstance().player.sendChatMessage("Example chat messsage");
		}));
	}
	
	@Override
	public Category getCategory()
	{
		return Categories.MAIN;
	}
	
	@Override
	public String getTitle()
	{
		return "Example Title";
	}
	
	@Override
	public String getTabTitle()
	{
		return "Example";
	}
	
	@Override
	public Content getActiveContent()
	{
		return WorldHandlerPlugin.EXAMPLE;
	}
}
