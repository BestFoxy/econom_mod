package ru.bestfoxy.econom.client;

import java.time.LocalTime;
import java.util.List;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import ru.bestfoxy.econom.common.CommonProxy;
import ru.bestfoxy.econom.utils.FakeStack;

@SideOnly(Side.CLIENT)
public class EventsFMLClient {
		
	@SubscribeEvent
	public void onRender(ItemTooltipEvent event) {		
		ItemStack stack = event.itemStack;
		for (FakeStack item : CommonProxy.ITEMS) {		
			ItemStack cfg = item.toItemStack();
			if ((cfg.getItem() == stack.getItem()) && ((cfg.getItemDamage() == stack.getItemDamage()) || (cfg.getItemDamage() == 32767)) && (((cfg.getTagCompound() == null) && (stack.getTagCompound() == null)) || (cfg.getTagCompound().equals(stack.getTagCompound())))) { 
				if(stack.stackSize <= 1) {
					event.toolTip.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("econom.price.name") + ":" + " " + EnumChatFormatting.WHITE + item.getCount());
				} else if(stack.stackSize >= 2) {
					event.toolTip.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("econom.price.name") + ":" + " " + EnumChatFormatting.WHITE + item.getCount());
					event.toolTip.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("econom.pricestack.name") + ":" + " " + EnumChatFormatting.WHITE + item.getCount() * stack.stackSize);
				}
			}
		}
	}
}