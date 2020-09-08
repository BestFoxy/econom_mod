package ru.bestfoxy.econom.client;

import java.util.List;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.common.MinecraftForge;
import ru.bestfoxy.econom.common.CommonProxy;
import ru.bestfoxy.econom.utils.FakeStack;

public class ClientProxy extends CommonProxy {	
	
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		MinecraftForge.EVENT_BUS.register(new EventsFMLClient());
	}

	public void Init(FMLInitializationEvent event) {
		super.Init(event);
	}
	
	public void postInit(FMLPostInitializationEvent event) {	
		super.postInit(event);
	}
	
	public static void loadConfig(List<String> lines) {	
		ITEMS.clear();
		for (String line : lines) {
			String item = line.substring(0, line.contains(".withTag(") ? line.indexOf(".withTag(") : line.lastIndexOf(':'));
			String metadata = line.substring(line.lastIndexOf(':') + 1, line.indexOf('@'));
			String count = line.substring(line.indexOf('@') + 1);
			String nbt = line.contains(".withTag(") ? line.substring(line.indexOf(".withTag(") + 9, line.lastIndexOf(')')) : null;
			ITEMS.add(new FakeStack(item, Short.parseShort(metadata), Integer.parseInt(count), nbt));
		}
	}
}