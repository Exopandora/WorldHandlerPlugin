package exopandora.worldhandlerplugin.content;

import exopandora.worldhandler.gui.category.Categories;
import exopandora.worldhandler.gui.category.Category;
import exopandora.worldhandler.gui.container.Container;
import exopandora.worldhandler.gui.content.Content;
import exopandora.worldhandler.gui.widget.button.GuiButtonBase;
import exopandora.worldhandler.util.ActionHelper;
import exopandora.worldhandlerplugin.WorldHandlerPlugin;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ContentExample extends Content
{
	@Override
	public void initButtons(Container container, int x, int y)
	{
		// Register default back and back to game buttons
		
		container.add(new GuiButtonBase(x, y + 96, 114, 20, new TranslatableComponent("gui.worldhandler.generic.back"), () -> ActionHelper.back(this)));
		container.add(new GuiButtonBase(x + 118, y + 96, 114, 20, new TranslatableComponent("gui.worldhandler.generic.backToGame"), ActionHelper::backToGame));
		
		// Register custom buttons
		
		container.add(new GuiButtonBase(x, y, 232, 20, new TextComponent("Example button"), () ->
		{
			Minecraft.getInstance().player.chat("Example chat messsage");
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
		return new TextComponent("Example Title");
	}
	
	@Override
	public MutableComponent getTabTitle()
	{
		return new TextComponent("Example");
	}
	
	@Override
	public Content getActiveContent()
	{
		return WorldHandlerPlugin.EXAMPLE;
	}
}
