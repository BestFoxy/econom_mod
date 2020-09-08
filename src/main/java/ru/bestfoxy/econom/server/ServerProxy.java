package ru.bestfoxy.econom.server;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.EventBus;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import ru.bestfoxy.econom.common.CommonProxy;
import ru.bestfoxy.econom.server.network.SyncConfigMessage;
import ru.bestfoxy.econom.utils.FakeStack;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.List;
import org.apache.logging.log4j.Logger;

public class ServerProxy extends CommonProxy {
	
	public static List<String> cfgStrings;
		
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		loadConfig(event.getSuggestedConfigurationFile());
		net.minecraftforge.common.MinecraftForge.EVENT_BUS.register(this);
		FMLCommonHandler.instance().bus().register(this);
	}
	
	public void init(FMLInitializationEvent event) {
		super.Init(event);
	}
	
	@SubscribeEvent
	public void onConnect(PlayerEvent.PlayerLoggedInEvent e) {
		network.sendTo(new SyncConfigMessage(cfgStrings), (net.minecraft.entity.player.EntityPlayerMP)e.player);
	}
	
	public static void loadConfig(File file) {
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			try
			{
				ITEMS.clear();
				List<String> lines = com.google.common.io.Files.readLines(file, Charset.forName("UTF-8"));
				for (String line : lines) {
					String item = line.substring(0, line.contains(".withTag(") ? line.indexOf(".withTag(") : line.lastIndexOf(':'));
					String metadata = line.substring(line.lastIndexOf(':') + 1, line.indexOf('@'));
					String count = line.substring(line.indexOf('@') + 1);
					String nbt = line.contains(".withTag(") ? line.substring(line.indexOf(".withTag(") + 9, line.lastIndexOf(')')) : null;
					ITEMS.add(new FakeStack(item, Short.parseShort(metadata), Integer.parseInt(count), nbt));
				}
				cfgStrings = lines;
			} catch (IOException e) {
				;
			}
		}
	}
}
